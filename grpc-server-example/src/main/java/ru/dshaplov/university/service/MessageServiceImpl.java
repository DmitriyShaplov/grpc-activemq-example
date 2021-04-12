package ru.dshaplov.university.service;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import ru.dshaplov.university.MessageRequest;
import ru.dshaplov.university.MessageResponse;
import ru.dshaplov.university.MessageServiceGrpc;

@Slf4j
public class MessageServiceImpl extends MessageServiceGrpc.MessageServiceImplBase {

    @Override
    public void logMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        log.info("Пришло сообщение по gRPC: {}", request.getMessage());
        MessageResponse response = MessageResponse.newBuilder().build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
