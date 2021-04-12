package ru.dshaplov.university.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dshaplov.university.service.MessageServiceImpl;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class GrpcServer {

    private Server server;

    @Bean
    public CommandLineRunner run() {
        return (args) -> {
            int port = 50051;
            server = ServerBuilder.forPort(port)
                    .addService(new MessageServiceImpl())
                    .build()
                    .start();
            log.info("Server started, listening on " + port);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    GrpcServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }));
            blockUntilShutdown();
        };
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
