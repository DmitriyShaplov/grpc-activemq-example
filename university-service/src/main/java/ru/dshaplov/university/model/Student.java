package ru.dshaplov.university.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_generator")
    @SequenceGenerator(name = "student_generator", sequenceName = "students_seq", allocationSize = 50)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    private String specialty;

    private String course;
}
