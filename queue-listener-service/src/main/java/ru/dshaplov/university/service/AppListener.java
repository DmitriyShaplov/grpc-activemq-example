package ru.dshaplov.university.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import ru.dshaplov.university.dto.StudentDto;
import ru.dshaplov.university.dto.TeacherDto;

import javax.jms.Message;
import javax.jms.ObjectMessage;

@Service
@Slf4j
public class AppListener {

    @JmsListener(destination = "${active-mq.student.topic}")
    public void receiveMessageStudent(final Message message) {
        try {
            ActiveMQTextMessage activeMQTextMessage = (ActiveMQTextMessage) message;
            log.info("Получен объект студента: {}", activeMQTextMessage.getText());
        } catch (Exception e) {
            log.error("Ошибка получения: {}", e.getMessage());
        }
    }

    @JmsListener(destination = "${active-mq.teacher.topic}")
    public void receiveMessageTeacher(final Message message) {
        try {
            ActiveMQTextMessage activeMQTextMessage = (ActiveMQTextMessage) message;
            log.info("Получен объект студента: {}", activeMQTextMessage.getText());
        } catch (Exception e) {
            log.error("Ошибка получения учителя: {}", e.getMessage());
        }
    }
}
