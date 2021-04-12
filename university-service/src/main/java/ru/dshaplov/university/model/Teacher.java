package ru.dshaplov.university.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_generator")
    @SequenceGenerator(name = "teacher_generator", sequenceName = "teachers_seq", allocationSize = 50)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    private String department;
}
