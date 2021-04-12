package ru.dshaplov.university.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dshaplov.university.dto.StudentDto;
import ru.dshaplov.university.model.Student;
import ru.dshaplov.university.repository.StudentRepository;
import ru.dshaplov.university.service.api.JmsProducer;
import ru.dshaplov.university.service.api.NotificationService;
import ru.dshaplov.university.service.api.StudentService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final NotificationService notificationService;

    @Override
    public StudentDto getById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найден студент по id: " + id));
        log.info("Найден студент с id: {}", id);
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        notificationService.notifyServices(studentDto);
        return studentDto;
    }

    @Override
    @Transactional
    public StudentDto save(StudentDto dto) {
        Student student = modelMapper.map(dto, Student.class);
        studentRepository.save(student);
        log.info("Сохранен студент с id: {}", student.getId());
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        notificationService.notifyServices(studentDto);
        return studentDto;
    }

    @Override
    public List<StudentDto> findAll() {
        List<Student> students = studentRepository.findAll();
        log.info("Найдены все студенты");
        return modelMapper.map(students, TypeToken.of(List.class).getType());
    }
}
