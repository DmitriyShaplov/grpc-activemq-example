package ru.dshaplov.university.service.api;

import ru.dshaplov.university.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    TeacherDto getById(Long id);
    TeacherDto save(TeacherDto dto);
    List<TeacherDto> findAll();
}
