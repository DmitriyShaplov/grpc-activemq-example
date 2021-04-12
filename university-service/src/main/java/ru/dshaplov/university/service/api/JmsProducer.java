package ru.dshaplov.university.service.api;

import ru.dshaplov.university.dto.StudentDto;
import ru.dshaplov.university.dto.TeacherDto;

public interface JmsProducer {
    void sendMessage(StudentDto studentDto);
    void sendMessage(TeacherDto teacherDto);
}
