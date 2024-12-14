package org.harshit.messenger.chat;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: messages.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ChatServiceGrpc {

  private ChatServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.harshit.messenger.chat.ChatService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.harshit.messenger.chat.Messages.ChatMessage,
      org.harshit.messenger.chat.Messages.ChatMessageResponse> getSendMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendMessage",
      requestType = org.harshit.messenger.chat.Messages.ChatMessage.class,
      responseType = org.harshit.messenger.chat.Messages.ChatMessageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.harshit.messenger.chat.Messages.ChatMessage,
      org.harshit.messenger.chat.Messages.ChatMessageResponse> getSendMessageMethod() {
    io.grpc.MethodDescriptor<org.harshit.messenger.chat.Messages.ChatMessage, org.harshit.messenger.chat.Messages.ChatMessageResponse> getSendMessageMethod;
    if ((getSendMessageMethod = ChatServiceGrpc.getSendMessageMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getSendMessageMethod = ChatServiceGrpc.getSendMessageMethod) == null) {
          ChatServiceGrpc.getSendMessageMethod = getSendMessageMethod =
              io.grpc.MethodDescriptor.<org.harshit.messenger.chat.Messages.ChatMessage, org.harshit.messenger.chat.Messages.ChatMessageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harshit.messenger.chat.Messages.ChatMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harshit.messenger.chat.Messages.ChatMessageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("SendMessage"))
              .build();
        }
      }
    }
    return getSendMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.harshit.messenger.chat.Messages.ChatMessage,
      org.harshit.messenger.chat.Messages.ChatMessageResponse> getStreamMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamMessage",
      requestType = org.harshit.messenger.chat.Messages.ChatMessage.class,
      responseType = org.harshit.messenger.chat.Messages.ChatMessageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<org.harshit.messenger.chat.Messages.ChatMessage,
      org.harshit.messenger.chat.Messages.ChatMessageResponse> getStreamMessageMethod() {
    io.grpc.MethodDescriptor<org.harshit.messenger.chat.Messages.ChatMessage, org.harshit.messenger.chat.Messages.ChatMessageResponse> getStreamMessageMethod;
    if ((getStreamMessageMethod = ChatServiceGrpc.getStreamMessageMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getStreamMessageMethod = ChatServiceGrpc.getStreamMessageMethod) == null) {
          ChatServiceGrpc.getStreamMessageMethod = getStreamMessageMethod =
              io.grpc.MethodDescriptor.<org.harshit.messenger.chat.Messages.ChatMessage, org.harshit.messenger.chat.Messages.ChatMessageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harshit.messenger.chat.Messages.ChatMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harshit.messenger.chat.Messages.ChatMessageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("StreamMessage"))
              .build();
        }
      }
    }
    return getStreamMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.harshit.messenger.chat.Messages.ChatMessage,
      org.harshit.messenger.chat.Messages.ChatMessage> getSendMessagesGRPCMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendMessagesGRPC",
      requestType = org.harshit.messenger.chat.Messages.ChatMessage.class,
      responseType = org.harshit.messenger.chat.Messages.ChatMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<org.harshit.messenger.chat.Messages.ChatMessage,
      org.harshit.messenger.chat.Messages.ChatMessage> getSendMessagesGRPCMethod() {
    io.grpc.MethodDescriptor<org.harshit.messenger.chat.Messages.ChatMessage, org.harshit.messenger.chat.Messages.ChatMessage> getSendMessagesGRPCMethod;
    if ((getSendMessagesGRPCMethod = ChatServiceGrpc.getSendMessagesGRPCMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getSendMessagesGRPCMethod = ChatServiceGrpc.getSendMessagesGRPCMethod) == null) {
          ChatServiceGrpc.getSendMessagesGRPCMethod = getSendMessagesGRPCMethod =
              io.grpc.MethodDescriptor.<org.harshit.messenger.chat.Messages.ChatMessage, org.harshit.messenger.chat.Messages.ChatMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendMessagesGRPC"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harshit.messenger.chat.Messages.ChatMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harshit.messenger.chat.Messages.ChatMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("SendMessagesGRPC"))
              .build();
        }
      }
    }
    return getSendMessagesGRPCMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChatServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChatServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChatServiceStub>() {
        @java.lang.Override
        public ChatServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChatServiceStub(channel, callOptions);
        }
      };
    return ChatServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChatServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChatServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChatServiceBlockingStub>() {
        @java.lang.Override
        public ChatServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChatServiceBlockingStub(channel, callOptions);
        }
      };
    return ChatServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChatServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChatServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChatServiceFutureStub>() {
        @java.lang.Override
        public ChatServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChatServiceFutureStub(channel, callOptions);
        }
      };
    return ChatServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void sendMessage(org.harshit.messenger.chat.Messages.ChatMessage request,
        io.grpc.stub.StreamObserver<org.harshit.messenger.chat.Messages.ChatMessageResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendMessageMethod(), responseObserver);
    }

    /**
     */
    default io.grpc.stub.StreamObserver<org.harshit.messenger.chat.Messages.ChatMessage> streamMessage(
        io.grpc.stub.StreamObserver<org.harshit.messenger.chat.Messages.ChatMessageResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getStreamMessageMethod(), responseObserver);
    }

    /**
     */
    default io.grpc.stub.StreamObserver<org.harshit.messenger.chat.Messages.ChatMessage> sendMessagesGRPC(
        io.grpc.stub.StreamObserver<org.harshit.messenger.chat.Messages.ChatMessage> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getSendMessagesGRPCMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ChatService.
   */
  public static abstract class ChatServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ChatServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ChatService.
   */
  public static final class ChatServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ChatServiceStub> {
    private ChatServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChatServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendMessage(org.harshit.messenger.chat.Messages.ChatMessage request,
        io.grpc.stub.StreamObserver<org.harshit.messenger.chat.Messages.ChatMessageResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<org.harshit.messenger.chat.Messages.ChatMessage> streamMessage(
        io.grpc.stub.StreamObserver<org.harshit.messenger.chat.Messages.ChatMessageResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getStreamMessageMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<org.harshit.messenger.chat.Messages.ChatMessage> sendMessagesGRPC(
        io.grpc.stub.StreamObserver<org.harshit.messenger.chat.Messages.ChatMessage> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getSendMessagesGRPCMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ChatService.
   */
  public static final class ChatServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ChatServiceBlockingStub> {
    private ChatServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChatServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.harshit.messenger.chat.Messages.ChatMessageResponse sendMessage(org.harshit.messenger.chat.Messages.ChatMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendMessageMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ChatService.
   */
  public static final class ChatServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ChatServiceFutureStub> {
    private ChatServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChatServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.harshit.messenger.chat.Messages.ChatMessageResponse> sendMessage(
        org.harshit.messenger.chat.Messages.ChatMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_MESSAGE = 0;
  private static final int METHODID_STREAM_MESSAGE = 1;
  private static final int METHODID_SEND_MESSAGES_GRPC = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((org.harshit.messenger.chat.Messages.ChatMessage) request,
              (io.grpc.stub.StreamObserver<org.harshit.messenger.chat.Messages.ChatMessageResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STREAM_MESSAGE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.streamMessage(
              (io.grpc.stub.StreamObserver<org.harshit.messenger.chat.Messages.ChatMessageResponse>) responseObserver);
        case METHODID_SEND_MESSAGES_GRPC:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sendMessagesGRPC(
              (io.grpc.stub.StreamObserver<org.harshit.messenger.chat.Messages.ChatMessage>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSendMessageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.harshit.messenger.chat.Messages.ChatMessage,
              org.harshit.messenger.chat.Messages.ChatMessageResponse>(
                service, METHODID_SEND_MESSAGE)))
        .addMethod(
          getStreamMessageMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              org.harshit.messenger.chat.Messages.ChatMessage,
              org.harshit.messenger.chat.Messages.ChatMessageResponse>(
                service, METHODID_STREAM_MESSAGE)))
        .addMethod(
          getSendMessagesGRPCMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              org.harshit.messenger.chat.Messages.ChatMessage,
              org.harshit.messenger.chat.Messages.ChatMessage>(
                service, METHODID_SEND_MESSAGES_GRPC)))
        .build();
  }

  private static abstract class ChatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ChatServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.harshit.messenger.chat.Messages.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ChatService");
    }
  }

  private static final class ChatServiceFileDescriptorSupplier
      extends ChatServiceBaseDescriptorSupplier {
    ChatServiceFileDescriptorSupplier() {}
  }

  private static final class ChatServiceMethodDescriptorSupplier
      extends ChatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ChatServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ChatServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ChatServiceFileDescriptorSupplier())
              .addMethod(getSendMessageMethod())
              .addMethod(getStreamMessageMethod())
              .addMethod(getSendMessagesGRPCMethod())
              .build();
        }
      }
    }
    return result;
  }
}
