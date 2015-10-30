
package io.grpc.examples.helloworld;

import com.google.common.primitives.Ints;
import com.google.protobuf.ByteString;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.lang.Integer;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
public class HelloWorldServer {
    private static final Logger logger = Logger.getLogger(HelloWorldServer.class.getName());

    /* The port on which the server should run */
    private int port = 50051;
    private Server server;

    private void start() throws Exception {
        server = ServerBuilder.forPort(port)
                .addService(io.grpc.examples.helloworld.GreeterGrpc.bindService(new GreeterImpl()))
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                HelloWorldServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws Exception {
        final HelloWorldServer server = new HelloWorldServer();
        server.start();
        server.blockUntilShutdown();
    }

    private class GreeterImpl implements io.grpc.examples.helloworld.GreeterGrpc.Greeter {

        @Override
        public void sayHello(io.grpc.examples.helloworld.HelloRequest req, StreamObserver<io.grpc.examples.helloworld.HelloResponse> responseObserver) {
            long recvTime = System.currentTimeMillis();
            HelloResponse reply = HelloResponse.newBuilder().setMessage("Hello " + req.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            long sentTime = System.currentTimeMillis();
            log("Request received at:" + recvTime);
            log("Response sent at:" + sentTime);
        }

        @Override
        public io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloByte> clientStream(
               final io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloByte> responseObserver) {
            return new StreamObserver<io.grpc.examples.helloworld.HelloByte>() {
                long startTime = System.currentTimeMillis();
                int pointCount = 0;

                @Override
                public void onNext(io.grpc.examples.helloworld.HelloByte helloByte) {
                    //long recvTime = System.currentTimeMillis();
                    pointCount++;
                    //log("Received byte at: " + recvTime);
                }

                @Override
                public void onError(Throwable t) {
                    logger.log(Level.WARNING, "Encountered error in recordRoute", t);
                }

                @Override
                public void onCompleted() {
                    long endTime = System.currentTimeMillis();
                    responseObserver.onNext(HelloByte.newBuilder().setNum(ByteString.copyFrom(Ints.toByteArray(pointCount))).build());
                    responseObserver.onCompleted();
                    log("Completed receipt at: " + endTime);
                }
            };
        }

        @Override
        public void serverStream(io.grpc.examples.helloworld.HelloByte request,
                                 io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloByte> responseObserver) {


        }

    }

    public static long getCpuTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadCpuTime() : 0L;
    }

    /**
     * Get user time in nanoseconds.
     */
    public static long getUserTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadUserTime() : 0L;
    }

    /**
     * Get system time in nanoseconds.
     */
    public static long getSystemTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                (bean.getCurrentThreadCpuTime() - bean.getCurrentThreadUserTime()) : 0L;
    }

    static void log(Object... o) {
        for (Object obj : o) {
            System.out.println(obj);
        }
    }

}
