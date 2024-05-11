package com.eric.demo.dto;
import java.util.Set;

import com.eric.demo.models.Note;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentNoteDTO {
    private Long studentId;
    private String studentName;
    private String studentEmail;
    private String studentPhone;
    private Set<Note> notes;
}
