package com.eric.demo.models;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("notes")
public class Note {
    @Id
    private BigInteger noteId;
    private String noteTitle;
    private String noteContent;
    private LocalDate noteDate;
    @Column("student_id")
    private BigInteger studentId;
    @Column("teacher_id")
    private BigInteger teacherId;

    // Getters, setters, and other methods
}
