package com.eric.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("notes")
public class Note {
    @Id
    private Long noteId;
    private String noteTitle;
    private String noteContent;
    private LocalDate noteDate;
    @Column("student_id")
    private Long studentId;
    @Column("teacher_id")
    private Long teacherId;

    // Getters, setters, and other methods
}
