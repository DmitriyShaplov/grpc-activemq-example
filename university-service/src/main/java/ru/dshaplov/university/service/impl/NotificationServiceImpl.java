package ru.dshaplov.university.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dshaplov.university.dto.StudentDto;
import ru.dshaplov.university.dto.TeacherDto;
import ru.dshaplov.university.grpc.LogClient;
import ru.dshaplov.university.service.api.JmsProducer;
import ru.dshaplov.university.service.api.NotificationService;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JmsProducer jmsProducer;
    private final LogClient logClient;
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public void notifyServices(StudentDto dto) {
        jmsProducer.sendMessage(dto);
        logClient.sendLogToServer(objectMapper.writeValueAsString(dto));
    }

    @Override
    @SneakyThrows
    public void notifyServices(TeacherDto dto) {
        jmsProducer.sendMessage(dto);
        logClient.sendLogToServer(objectMapper.writeValueAsString(dto));
    }
}
