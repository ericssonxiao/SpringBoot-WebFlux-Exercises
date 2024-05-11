package com.eric.demo.models;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("teachers")
public class Teacher {
    @Id
    private BigInteger teacherId;
    private String teacherName;
    private String teacherEmail;
    private String teacherPhone;

    // Getters, setters, and other methods
}
