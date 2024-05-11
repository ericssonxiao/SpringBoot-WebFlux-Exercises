package com.eric.demo.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import com.eric.demo.models.Note;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherNoteDTO {
    private Long teacherId;
    private String teacherName;
    private String teacherEmail;
    private String teacherPhone;
    private Set<Note> notes;
}
