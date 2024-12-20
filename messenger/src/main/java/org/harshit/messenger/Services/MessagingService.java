package org.harshit.messenger.Services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.harshit.messenger.Controllers.UserInfoController;
import org.harshit.messenger.chat.ChatServiceGrpc.ChatServiceImplBase;
import org.harshit.messenger.chat.Messages.ChatMessage;
import org.harshit.messenger.chat.Messages.ChatMessageResponse;
import org.harshit.messenger.utils.UserMessage;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class MessagingService extends ChatServiceImplBase{
    private Logger logger = Logger.getLogger(MessagingService.class.getName());

    private Map<String, StreamObserver<ChatMessage>> userMap = new HashMap<>();

    @Override
    public void sendMessage(ChatMessage request, StreamObserver<ChatMessageResponse> responseObserver) {
        ChatMessageResponse response = ChatMessageResponse.newBuilder().setDeliveryStatus(true).build();
        logger.log(
                Level.INFO, "Send Message endpoint called with Username: "+request.getUser()+", Friend: "+request.getFriend());

        logger.log(Level.INFO, "Message: "+request.getMessage());
        logger.log(Level.INFO, "TimeStamp: "+request.getTimestamp());

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<ChatMessage> streamMessage(StreamObserver<ChatMessageResponse> responseObserver) {
        return new StreamObserver<ChatMessage>() {
            @Override
            public void onNext(ChatMessage message) {
                logger.log(Level.INFO, "Sent a message, "+message.getMessage()+", from user: "+message.getUser()+" to user: "+message.getFriend()+" at time: "+message.getTimestamp());
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.WARNING, "Found error");
                responseObserver.onNext(ChatMessageResponse.newBuilder().setDeliveryStatus(false).build());
                responseObserver.onCompleted();
            }

            @Override
            public void onCompleted() {
                ChatMessageResponse response = ChatMessageResponse.newBuilder().setDeliveryStatus(true).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }

        };
    }

    @Override
    public StreamObserver<ChatMessage> sendMessagesGRPC(StreamObserver<ChatMessage> responseObserver) {
        StreamObserver<ChatMessage> requestObserver = new StreamObserver<ChatMessage>() {
            @Override
            public void onNext(ChatMessage message) {
                // Creating message to be stored in the database
                UUID id = UUID.randomUUID();
                UserMessage msg_to_store = new UserMessage(id.toString() ,message.getUser().replace("\"", ""), message.getFriend().replace("\"", ""), message.getMessage(), message.getTimestamp(), false);
                //
                logger.log(Level.INFO, "Sent a message, "+message.getMessage()+", from user: "+message.getUser()+" to user: "+message.getFriend()+" at time: "+message.getTimestamp());
                String user = message.getUser();
                printUserMap();
                user = user.replace("\"", "");
                logger.log(Level.INFO, "New user name: "+user);
                if(!userMap.containsKey(user)){
                    logger.log(Level.INFO, "Adding user to the map");
                    userMap.put(user, responseObserver);
                }

                String friend = message.getFriend().replace("\"", "");
                if(!userMap.containsKey(friend)) {
                    logger.log(Level.INFO, friend+" is not online");
                    CompletableFuture.runAsync(()->{
                        UserInfoController.instance.storeMessage(msg_to_store);
                    });
                    return;
                }
                if(isTerminationMessage(message)) {
                    logger.log(Level.INFO, "Removing user: "+message.getUser());
                    userMap.remove(message.getUser());
                    return;
                }

                StreamObserver<ChatMessage> resFriend = userMap.get(friend);
                resFriend.onNext(message);
                CompletableFuture.runAsync(()->{
                    msg_to_store.setBReceived(true);
                    UserInfoController.instance.storeMessage(msg_to_store);
                });
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.WARNING, "Found error");
                if(t instanceof StatusRuntimeException) {
                    StatusRuntimeException ex = (StatusRuntimeException) t;
                    if(ex.getStatus().getCode() == Status.Code.CANCELLED) {
                        logger.log(Level.INFO, "Client is cancelled");
                        // Need to remove the client from the usermap and watever else is required
                        userMap = userMap.entrySet()
                            .stream()
                            .filter(entry -> !entry.getValue().equals(responseObserver))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                        printUserMap();
                    }
                }
            }


            @Override
            public void onCompleted() {
                logger.log(Level.INFO, "On complete called");
                responseObserver.onCompleted();
            }
        };

        // Add both the observers to the dictionary

        return requestObserver;
    }

    private Boolean isTerminationMessage(ChatMessage message) {
        if(message.getFriend() == message.getUser()) {
            return true;
        }
        return false;
    }

    private void printUserMap() {
        for (String iterable_element : userMap.keySet()) {
            logger.log(Level.INFO, iterable_element);
        }
    }

};

