package ru.dshaplov.university.service.api;

import ru.dshaplov.university.dto.StudentDto;
import ru.dshaplov.university.dto.TeacherDto;

public interface NotificationService {

    void notifyServices(StudentDto dto);
    void notifyServices(TeacherDto dto);
}
