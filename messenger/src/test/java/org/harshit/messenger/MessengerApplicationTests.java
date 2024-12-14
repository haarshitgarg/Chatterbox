package org.harshit.messenger;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.harshit.messenger.Services.MessagingService;
import org.harshit.messenger.chat.ChatServiceGrpc;
import org.harshit.messenger.chat.ChatServiceGrpc.ChatServiceBlockingStub;
import org.harshit.messenger.chat.ChatServiceGrpc.ChatServiceStub;
import org.harshit.messenger.chat.Messages.ChatMessage;
import org.harshit.messenger.chat.Messages.ChatMessageResponse;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.testing.GrpcCleanupRule;

@RunWith(JUnit4.class)
public class MessengerApplicationTests {
    private Logger logger = Logger.getLogger(MessengerApplicationTests.class.getName());

    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

	@Test
	public void testStreamMessage() throws Exception{
        logger.log(Level.INFO, "Testing Stream Message service");

        String serverName = InProcessServerBuilder.generateName();
        grpcCleanup.register(InProcessServerBuilder.forName(serverName).directExecutor().addService(new MessagingService()).build().start());

        StreamObserver<ChatMessageResponse> observer = new StreamObserver<ChatMessageResponse>() {
            @Override
            public void onNext(ChatMessageResponse response) {
                logger.log(Level.INFO, "Response status: "+response.getDeliveryStatus());
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.WARNING, "Server encountered an error");
            }

            @Override 
            public void onCompleted() {
                logger.log(Level.INFO, "Server has read all the messages");
            }
        };

        ChatServiceStub serviceStub = ChatServiceGrpc.newStub(grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));
        StreamObserver<ChatMessage> requestObserver = serviceStub.streamMessage(observer);
        requestObserver.onNext(ChatMessage.newBuilder().setUser("Harshit").setFriend("Tanvi").setTimestamp(2020).setMessage("Hello").build());
        requestObserver.onNext(ChatMessage.newBuilder().setUser("Harshit").setFriend("Rishika").setTimestamp(2020).setMessage("Hello").build());
        requestObserver.onNext(ChatMessage.newBuilder().setUser("Harshit").setFriend("Jhanvi").setTimestamp(2020).setMessage("Hello").build());
        requestObserver.onNext(ChatMessage.newBuilder().setUser("Harshit").setFriend("Tushar").setTimestamp(2020).setMessage("Hello").build());
        requestObserver.onCompleted();
	}

    @Test
    public void testMultipleClients() throws Exception {
        logger.log(Level.WARNING, "Testing Multiple clients");

        String serverName = InProcessServerBuilder.generateName();
        grpcCleanup.register(InProcessServerBuilder.forName(serverName).directExecutor().addService(new MessagingService()).build().start());

        StreamObserver<ChatMessageResponse> responseObserver1 = new StreamObserver<ChatMessageResponse>() {
            @Override
            public void onNext(ChatMessageResponse response){
            }

            @Override
            public void onError(Throwable t){
            }

            @Override
            public void onCompleted() {
            }

        };

        StreamObserver<ChatMessageResponse> responseObserver2 = new StreamObserver<ChatMessageResponse>() {
            @Override
            public void onNext(ChatMessageResponse response){
            }

            @Override
            public void onError(Throwable t){
            }

            @Override
            public void onCompleted() {
            }

        };

        ChatServiceStub serviceStub = ChatServiceGrpc.newStub(grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));

        StreamObserver<ChatMessage> requestObserver1 = serviceStub.streamMessage(responseObserver1);
        StreamObserver<ChatMessage> requestObserver2 = serviceStub.streamMessage(responseObserver2);

        requestObserver1.onNext(ChatMessage.newBuilder().setUser("Harshit").setFriend("Tanvi").setTimestamp(2020).setMessage("Hello").build());
        requestObserver2.onNext(ChatMessage.newBuilder().setUser("Devang").setFriend("Rishika").setTimestamp(2020).setMessage("Hello").build());
        requestObserver1.onNext(ChatMessage.newBuilder().setUser("Harshit").setFriend("Jhanvi").setTimestamp(2020).setMessage("Hello").build());
        requestObserver2.onNext(ChatMessage.newBuilder().setUser("Devang").setFriend("Tushar").setTimestamp(2020).setMessage("Hello").build());

        requestObserver1.onCompleted();
        requestObserver2.onCompleted();
    }

    @Test
    public void testBiDiStream() throws Exception{
        String serverName = InProcessServerBuilder.generateName();
        grpcCleanup.register(InProcessServerBuilder.forName(serverName).directExecutor().addService(new MessagingService()).build().start());

        ChatServiceStub stubcode = ChatServiceGrpc.newStub(grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));

        StreamObserver<ChatMessage> resOb1 = new StreamObserver<ChatMessage>() {
            @Override
            public void onNext(ChatMessage response){
                logger.log(Level.INFO, "[Harshit] Response from "+response.getUser()+": "+response.getMessage());
            }

            @Override
            public void onError(Throwable t){
            }

            @Override
            public void onCompleted() {
            }
        };

        StreamObserver<ChatMessage> resOb2 = new StreamObserver<ChatMessage>() {
            @Override
            public void onNext(ChatMessage response){
                logger.log(Level.INFO, "[Tanvi] Response from "+response.getUser()+": "+response.getMessage());
            }

            @Override
            public void onError(Throwable t){
            }

            @Override
            public void onCompleted() {
            }
        };

        StreamObserver<ChatMessage> reqOb1 = stubcode.sendMessagesGRPC(resOb1);
        StreamObserver<ChatMessage> reqOb2 = stubcode.sendMessagesGRPC(resOb2);

        reqOb1.onNext(ChatMessage.newBuilder().setUser("Harshit").setFriend("Tanvi").setMessage("Hello from harshit").build());
        reqOb1.onNext(ChatMessage.newBuilder().setUser("Harshit").setFriend("Tanvi").setMessage("Hello from harshit").build());
        reqOb1.onNext(ChatMessage.newBuilder().setUser("Harshit").setFriend("Rishika").setMessage("Hello from harshit").build());
        reqOb2.onNext(ChatMessage.newBuilder().setUser("Tanvi").setFriend("Harshit").setMessage("Hello from Tanvi").build());
        reqOb1.onNext(ChatMessage.newBuilder().setUser("Harshit").setFriend("Tanvi").setMessage("What uppp").build());
        reqOb2.onNext(ChatMessage.newBuilder().setUser("Tanvi").setFriend("Harshit").setMessage("Testing testing testing").build());

        reqOb1.onNext(ChatMessage.newBuilder().setUser("Harshit").setFriend("Harshit").setMessage("Hello from harshit").build());
        reqOb2.onNext(ChatMessage.newBuilder().setUser("Tanvi").setFriend("Tanvi").setMessage("Hello from harshit").build());
        reqOb1.onCompleted();
        reqOb2.onCompleted();
    }


}
