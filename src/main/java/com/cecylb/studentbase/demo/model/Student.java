package com.cecylb.studentbase.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long student_id;
    @NotNull(message = "Name cannot be empty!")
    @Min(3)
    private String name;
    @NotNull(message = "Surname cannot be empty!")
    private String surname;
    private String patronymic;
    private Date birthday;
    @NotNull(message = "Student must belong to a group!")
    private int student_group;
}
