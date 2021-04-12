package ru.dshaplov.university.grpc;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.dshaplov.university.MessageRequest;
import ru.dshaplov.university.MessageResponse;
import ru.dshaplov.university.MessageServiceGrpc;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LogClient {

    private MessageServiceGrpc.MessageServiceBlockingStub blockingStub;
    private ManagedChannel channel;

    @Value("${grpc.target:localhost:50051}")
    private String grpcTarget;

    @PostConstruct
    public void init() {
        channel = ManagedChannelBuilder.forTarget(grpcTarget)
                .usePlaintext()
                .build();
        blockingStub = MessageServiceGrpc.newBlockingStub(channel);
    }

    public void sendLogToServer(String message) {
        MessageRequest request = MessageRequest.newBuilder()
                .setMessage(message).build();
        try {
            MessageResponse messageResponse = blockingStub.logMessage(request);
        } catch (StatusRuntimeException e) {
            log.warn("RPC failed: {}", e.getStatus());
        }
    }

    @PreDestroy
    @SneakyThrows
    public void destroy() {
        if (channel != null) {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
