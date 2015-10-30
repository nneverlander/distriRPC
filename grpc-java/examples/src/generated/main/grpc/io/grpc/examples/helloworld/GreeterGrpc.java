package io.grpc.examples.helloworld;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class GreeterGrpc {

  private GreeterGrpc() {}

  public static final String SERVICE_NAME = "helloworld.Greeter";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<io.grpc.examples.helloworld.HelloRequest,
      io.grpc.examples.helloworld.HelloResponse> METHOD_SAY_HELLO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "helloworld.Greeter", "SayHello"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.examples.helloworld.HelloRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.examples.helloworld.HelloResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<io.grpc.examples.helloworld.HelloByte,
      io.grpc.examples.helloworld.HelloByte> METHOD_CLIENT_STREAM =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING,
          generateFullMethodName(
              "helloworld.Greeter", "ClientStream"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.examples.helloworld.HelloByte.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.examples.helloworld.HelloByte.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<io.grpc.examples.helloworld.HelloByte,
      io.grpc.examples.helloworld.HelloByte> METHOD_SERVER_STREAM =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "helloworld.Greeter", "ServerStream"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.examples.helloworld.HelloByte.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.grpc.examples.helloworld.HelloByte.getDefaultInstance()));

  public static GreeterStub newStub(io.grpc.Channel channel) {
    return new GreeterStub(channel);
  }

  public static GreeterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GreeterBlockingStub(channel);
  }

  public static GreeterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GreeterFutureStub(channel);
  }

  public static interface Greeter {

    public void sayHello(io.grpc.examples.helloworld.HelloRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloResponse> responseObserver);

    public io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloByte> clientStream(
        io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloByte> responseObserver);

    public void serverStream(io.grpc.examples.helloworld.HelloByte request,
        io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloByte> responseObserver);
  }

  public static interface GreeterBlockingClient {

    public io.grpc.examples.helloworld.HelloResponse sayHello(io.grpc.examples.helloworld.HelloRequest request);

    public java.util.Iterator<io.grpc.examples.helloworld.HelloByte> serverStream(
        io.grpc.examples.helloworld.HelloByte request);
  }

  public static interface GreeterFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<io.grpc.examples.helloworld.HelloResponse> sayHello(
        io.grpc.examples.helloworld.HelloRequest request);
  }

  public static class GreeterStub extends io.grpc.stub.AbstractStub<GreeterStub>
      implements Greeter {
    private GreeterStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterStub(channel, callOptions);
    }

    @java.lang.Override
    public void sayHello(io.grpc.examples.helloworld.HelloRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SAY_HELLO, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloByte> clientStream(
        io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloByte> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(METHOD_CLIENT_STREAM, getCallOptions()), responseObserver);
    }

    @java.lang.Override
    public void serverStream(io.grpc.examples.helloworld.HelloByte request,
        io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloByte> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_SERVER_STREAM, getCallOptions()), request, responseObserver);
    }
  }

  public static class GreeterBlockingStub extends io.grpc.stub.AbstractStub<GreeterBlockingStub>
      implements GreeterBlockingClient {
    private GreeterBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public io.grpc.examples.helloworld.HelloResponse sayHello(io.grpc.examples.helloworld.HelloRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_SAY_HELLO, getCallOptions()), request);
    }

    @java.lang.Override
    public java.util.Iterator<io.grpc.examples.helloworld.HelloByte> serverStream(
        io.grpc.examples.helloworld.HelloByte request) {
      return blockingServerStreamingCall(
          getChannel().newCall(METHOD_SERVER_STREAM, getCallOptions()), request);
    }
  }

  public static class GreeterFutureStub extends io.grpc.stub.AbstractStub<GreeterFutureStub>
      implements GreeterFutureClient {
    private GreeterFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.examples.helloworld.HelloResponse> sayHello(
        io.grpc.examples.helloworld.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SAY_HELLO, getCallOptions()), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final Greeter serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
      .addMethod(
        METHOD_SAY_HELLO,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              io.grpc.examples.helloworld.HelloRequest,
              io.grpc.examples.helloworld.HelloResponse>() {
            @java.lang.Override
            public void invoke(
                io.grpc.examples.helloworld.HelloRequest request,
                io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloResponse> responseObserver) {
              serviceImpl.sayHello(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_CLIENT_STREAM,
        asyncClientStreamingCall(
          new io.grpc.stub.ServerCalls.ClientStreamingMethod<
              io.grpc.examples.helloworld.HelloByte,
              io.grpc.examples.helloworld.HelloByte>() {
            @java.lang.Override
            public io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloByte> invoke(
                io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloByte> responseObserver) {
              return serviceImpl.clientStream(responseObserver);
            }
          }))
      .addMethod(
        METHOD_SERVER_STREAM,
        asyncServerStreamingCall(
          new io.grpc.stub.ServerCalls.ServerStreamingMethod<
              io.grpc.examples.helloworld.HelloByte,
              io.grpc.examples.helloworld.HelloByte>() {
            @java.lang.Override
            public void invoke(
                io.grpc.examples.helloworld.HelloByte request,
                io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloByte> responseObserver) {
              serviceImpl.serverStream(request, responseObserver);
            }
          })).build();
  }
}
