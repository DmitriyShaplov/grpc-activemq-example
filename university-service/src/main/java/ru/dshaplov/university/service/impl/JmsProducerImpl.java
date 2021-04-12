package ru.dshaplov.university.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.dshaplov.university.dto.StudentDto;
import ru.dshaplov.university.dto.TeacherDto;
import ru.dshaplov.university.service.api.JmsProducer;

@Service
@Slf4j
public class JmsProducerImpl implements JmsProducer {

    private final JmsTemplate jmsTemplate;

    @Value("${active-mq.student.topic}")
    private String studentTopic;
    @Value("${active-mq.teacher.topic}")
    private String teacherTopic;

    public JmsProducerImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendMessage(StudentDto studentDto) {
        try {
            log.info("Попытка послать сообщение в топик: {}", studentTopic);
            jmsTemplate.convertAndSend(studentTopic, studentDto);
        } catch (JmsException e) {
            log.error("Не удалось отправить сообщение по студентам");
        }
    }

    @Override
    public void sendMessage(TeacherDto teacherDto) {
        try {
            log.info("Попытка послать сообщение в топик: {}", teacherTopic);
            jmsTemplate.convertAndSend(teacherTopic, teacherDto);
        } catch (JmsException e) {
            log.error("Не удалось отправить сообщение по учителям");
        }
    }
}
