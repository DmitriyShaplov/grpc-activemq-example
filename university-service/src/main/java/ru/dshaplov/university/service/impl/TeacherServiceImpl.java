package ru.dshaplov.university.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dshaplov.university.dto.TeacherDto;
import ru.dshaplov.university.model.Teacher;
import ru.dshaplov.university.repository.TeacherRepository;
import ru.dshaplov.university.service.api.NotificationService;
import ru.dshaplov.university.service.api.TeacherService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;
    private final NotificationService notificationService;

    @Override
    public TeacherDto getById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найден учитель с id: " + id));
        log.info("Найден учитель с id: {}", id);
        TeacherDto teacherDto = modelMapper.map(teacher, TeacherDto.class);
        notificationService.notifyServices(teacherDto);
        return teacherDto;
    }

    @Override
    @Transactional
    public TeacherDto save(TeacherDto dto) {
        Teacher teacher = modelMapper.map(dto, Teacher.class);
        teacherRepository.save(teacher);
        log.info("Сохранен учитель с id: {}", teacher.getId());
        TeacherDto teacherDto = modelMapper.map(teacher, TeacherDto.class);
        notificationService.notifyServices(teacherDto);
        return teacherDto;
    }

    @Override
    public List<TeacherDto> findAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        log.info("Найдены все учителя");
        return modelMapper.map(teachers, TypeToken.of(List.class).getType());
    }
}
