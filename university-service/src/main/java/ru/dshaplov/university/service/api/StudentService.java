package ru.dshaplov.university.service.api;

import ru.dshaplov.university.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto getById(Long id);
    StudentDto save(StudentDto dto);
    List<StudentDto> findAll();
}
