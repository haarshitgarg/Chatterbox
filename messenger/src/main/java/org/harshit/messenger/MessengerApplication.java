package org.harshit.messenger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.harshit.messenger.Services.MessagingService;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;

public class MessengerApplication {
    private Server server;

    private final int port = 9000;

    private void runServer() throws IOException {
        System.out.println("[MESSENGER SERVER] Creating the server");
        this.server = Grpc.newServerBuilderForPort(this.port, InsecureServerCredentials.create())
            .addService(new MessagingService())
            .build()
            .start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("[MESSENGER SERVER] Stopping the server");
                    MessengerApplication.this.stop();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void stop() throws IOException, InterruptedException {
        if(this.server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilshutdown() throws InterruptedException {
        if(this.server != null) {
            server.awaitTermination();
        }
    }

	public static void main(String[] args) throws IOException, InterruptedException{

        System.out.println("##################################################");
        System.out.println("[MESSENGER SERVER] Starting gRPC server");

        final MessengerApplication application = new MessengerApplication();
        application.runServer();            // Throws Exception here
        System.out.println("[MESSENGER SERVER] Server running at port: "+application.port);
        System.out.println("##################################################");
        application.blockUntilshutdown();   // Throws Exception here
	}
}
