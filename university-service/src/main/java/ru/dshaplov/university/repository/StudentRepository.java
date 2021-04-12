package ru.dshaplov.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dshaplov.university.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
