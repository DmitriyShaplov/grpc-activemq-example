package ru.dshaplov.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dshaplov.university.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
