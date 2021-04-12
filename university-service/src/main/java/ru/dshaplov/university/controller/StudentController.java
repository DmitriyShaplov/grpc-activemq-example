package ru.dshaplov.university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dshaplov.university.dto.StudentDto;
import ru.dshaplov.university.service.api.StudentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{id}")
    public StudentDto getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PostMapping
    public StudentDto save(@RequestBody StudentDto dto) {
        return studentService.save(dto);
    }

    @GetMapping
    public List<StudentDto> findAll() {
        return studentService.findAll();
    }
}
