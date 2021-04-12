package ru.dshaplov.university.dto;

import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String fullName;
    private String specialty;
    private String course;
}
