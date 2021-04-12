package ru.dshaplov.university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dshaplov.university.dto.TeacherDto;
import ru.dshaplov.university.service.api.TeacherService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/{id}")
    public TeacherDto getById(@PathVariable Long id) {
        return teacherService.getById(id);
    }

    @PostMapping
    public TeacherDto save(@RequestBody TeacherDto dto) {
        return teacherService.save(dto);
    }

    @GetMapping
    public List<TeacherDto> findAll() {
        return teacherService.findAll();
    }
}
