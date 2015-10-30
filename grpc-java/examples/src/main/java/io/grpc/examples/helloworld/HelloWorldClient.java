
package io.grpc.examples.helloworld;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.lang.*;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Math;
import java.lang.String;
import java.lang.System;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.SchemaOutputResolver;
import sun.misc.Cleaner;

/**
 * A simple client that requests a greeting from the {@link HelloWorldServer}.
 */
public class HelloWorldClient {
    private static final Logger logger = Logger.getLogger(HelloWorldClient.class.getName());

    private final ManagedChannel channel;
    private final io.grpc.examples.helloworld.GreeterGrpc.GreeterBlockingStub blockingStub;
    private static io.grpc.examples.helloworld.GreeterGrpc.GreeterStub asyncStub;

    /**
     * Construct client connecting to HelloWorld server at {@code host:port}.
     */
    public HelloWorldClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        blockingStub = io.grpc.examples.helloworld.GreeterGrpc.newBlockingStub(channel);
        asyncStub = io.grpc.examples.helloworld.GreeterGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Say hello to server.
     *
     */


    public double greet(String name) {
        try {
            //logger.info("Will try to greet " + name + " ...");
            long sentTime = System.currentTimeMillis();
            long begin = getCpuTime();
            io.grpc.examples.helloworld.HelloRequest request = io.grpc.examples.helloworld.HelloRequest.newBuilder().setName(name).build();
            long packTime = getCpuTime();
            io.grpc.examples.helloworld.HelloResponse response = blockingStub.sayHello(request);
            String str = response.getMessage();
            long recvTime = System.currentTimeMillis();
            log("Time for marshalling a string of length " + name.length() + " is " + (packTime - begin) / (double) 1000000 + "ms");
            log("Rtt: " + (recvTime - sentTime) + "ms");
            log("Request sent at: " + sentTime);
            log("Response received at:" + recvTime);
            //logger.info("Greeting: " + response.getMessage());
            return (packTime - begin) / (double) 1000000;
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "RPC failed", e);
            return -1;
        }
    }

    /**
     * Say hello to server.
     */
    public double greetInt(int name) {
        try {
            //logger.info("Will try to greet " + name + " ...");

            long begin = getCpuTime();
            io.grpc.examples.helloworld.HelloInt request = io.grpc.examples.helloworld.HelloInt.newBuilder().setNum(name).build();
            long packTime = getCpuTime();
            log("Time for marshalling a int " + name + " is " + (packTime - begin) / (double) 1000000 + "ms");
            return (packTime - begin) / (double) 1000000;
            //logger.info("Greeting: " + response.getMessage());
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "RPC failed", e);
            return -1;
        }
    }

    /**
     * Say hello to server.
     */
    public double greetDouble(double name) {
        try {
            //logger.info("Will try to greet " + name + " ...");

            long begin = getCpuTime();
            io.grpc.examples.helloworld.HelloDouble request = io.grpc.examples.helloworld.HelloDouble.newBuilder().setNum(name).build();
            long packTime = getCpuTime();
            log("Time for marshalling a double " + name + " is " + (packTime - begin) / (double) 1000000 + "ms");
            return (packTime - begin) / (double) 1000000;
            //logger.info("Greeting: " + response.getMessage());
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "RPC failed", e);
            return -1;
        }
    }

    /**
     * Say hello to server.
     */
    public double greetComplex(double dbl, int num, String str) {
        try {
            //logger.info("Will try to greet " + name + " ...");

            long begin = getCpuTime();
            io.grpc.examples.helloworld.HelloDouble hDbl = io.grpc.examples.helloworld.HelloDouble.newBuilder().setNum(dbl).build();
            io.grpc.examples.helloworld.HelloInt hInt = io.grpc.examples.helloworld.HelloInt.newBuilder().setNum(num).build();
            io.grpc.examples.helloworld.HelloRequest request = io.grpc.examples.helloworld.HelloRequest.newBuilder().setName(str).build();
            io.grpc.examples.helloworld.HelloComplex cmplx = io.grpc.examples.helloworld.HelloComplex.newBuilder().setDbl(hDbl).setInt(hInt).setStr(request).build();
            long packTime = getCpuTime();
            log("Time for marshalling a complex structure is " + (packTime - begin) / (double) 1000000 + "ms");
            return (packTime - begin) / (double) 1000000;
            //logger.info("Greeting: " + response.getMessage());
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "RPC failed", e);
            return -1;
        }
    }

    static void log(Object... o) {
        for (Object obj : o) {
            System.out.println(obj);
        }
    }

    /**
     * Get CPU time in nanoseconds.
     */
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

    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting.
     */
    public static void main(String[] args) throws Exception {
        HelloWorldClient client = new HelloWorldClient("localhost", 50051);
        try {
      /* Access a service running on the local machine on port 50051 */
            String user = "wor";
            List<String> list = new ArrayList<String>();
            list.add(user);
            byte[] b = new byte[1024];
            byte b1 = '2';
            Arrays.fill(b, b1);
            byte[] b2 = new byte[1024 * 10];
            Arrays.fill(b2, b1);
            byte[] b3 = new byte[1024 * 100];
            Arrays.fill(b3, b1);
            byte[] b4 = new byte[1024 * 50];
            Arrays.fill(b4, b1);
            list.add(user);
            list.add(new String(b));
            list.add(new String(b2));
            list.add(new String(b3));
            list.add(new String(b4));

            List<Double> ls0 = new ArrayList<Double>();
            for (String s : list) {
                double score = client.greet(user);
                ls0.add(score);
            }

            List<Double> ls = new ArrayList<Double>();
            for (String s : list) {
               double score = client.greet(new String(b));
                ls.add(score);
            }
            List<Double> ls1 = new ArrayList<Double>();
            for (String s : list) {
                double score = client.greet(new String(b2));
                ls1.add(score);
            }
            List<Double> ls2 = new ArrayList<Double>();
            for (String s : list) {
                double score = client.greetInt(22);
                ls2.add(score);
            }
            List<Double> ls3 = new ArrayList<Double>();
            for (String s : list) {
                double score = client.greetDouble(22.45);
                ls3.add(score);
            }
            List<Double> ls4 = new ArrayList<Double>();
            for (String s : list) {
                double score = client.greetComplex(2.5,2,"44");
                ls4.add(score);
            }

            Collections.sort(ls0);
            log("median: " + ls0.get(2));

            Collections.sort(ls);
            log("median: " + ls.get(2));

            Collections.sort(ls1);
            log("median: " + ls1.get(2));

            Collections.sort(ls2);
            log("median: " + ls2.get(2));

            Collections.sort(ls3);
            log("median: " + ls3.get(2));

            Collections.sort(ls4);
            log("median: " + ls4.get(2));
            /*client.greetInt(22);
            client.greetDouble(22.45);
            client.greetComplex(2.5, 2, "44");*/


            //Client stream
            //clientStream();

            /*int totalSize = packets*size;
            double bandwidth = (totalSize / totalTime) / 1024; //MBps*/

        } finally {
            client.shutdown();
        }
    }

    /**
     * Async client-streaming example. Sends {@code numPoints} randomly chosen points from {@code
     * features} with a variable delay in between. Prints the statistics when they are sent from the
     * server.
     */
    private static void clientStream() throws Exception {
        //final SettableFuture<Void> finishFuture = SettableFuture.create();
        io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloByte> responseObserver = new io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloByte>() {
            @Override
            public void onNext(io.grpc.examples.helloworld.HelloByte summary) {
                ByteString num = summary.getNum();
                int total = new BigInteger(num.toByteArray()).intValue();
                log("Total num sent: " + total);
                // return total;
            }

            @Override
            public void onError(Throwable t) {
                //finishFuture.setException(t);
            }

            @Override
            public void onCompleted() {
                //finishFuture.set(null);
            }
        };

        io.grpc.stub.StreamObserver<io.grpc.examples.helloworld.HelloByte> requestObserver = asyncStub.clientStream(responseObserver);
        try {
            int size = 1024 * 50;
            //for (int i = 1; i < 10; i++) {
            //size = (int) Math.pow(size, i);
            byte[] bytes1 = new byte[size];
            byte b10 = '2';
            Arrays.fill(bytes1, b10);
            ByteString bytes = ByteString.copyFrom(bytes1);
            long startTime = System.currentTimeMillis();
            for (int j = 0; j < 10000; j++) {

                    /*try {
                        Thread.sleep(2000);
                    } catch (java.lang.Exception e) {

                    }*/

                io.grpc.examples.helloworld.HelloByte helloByte = io.grpc.examples.helloworld.HelloByte.newBuilder().setNum(bytes).build();

                // Sleep for a bit before sending the next one.3
                /*Thread.sleep(rand.nextInt(1000) + 500);
                if (finishFuture.isDone()) {
                    break;
                }*/

                requestObserver.onNext(helloByte);

            }
            //}
            log("Start time: " + startTime);
            requestObserver.onCompleted();
            //finishFuture.get();
        } catch (Exception e) {
            requestObserver.onError(e);
            logger.log(Level.WARNING, "Failed", e);
            throw e;
        }
    }
}
