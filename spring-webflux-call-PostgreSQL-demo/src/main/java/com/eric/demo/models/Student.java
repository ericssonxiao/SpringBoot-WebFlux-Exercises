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
@Table("students")
public class Student {
    @Id
    private BigInteger studentId;
    private String studentName;
    private String studentEmail;
    private String studentPhone;

    // Getters, setters, and other methods
}
