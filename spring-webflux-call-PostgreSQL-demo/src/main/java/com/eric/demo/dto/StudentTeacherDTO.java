package com.eric.demo.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentTeacherDTO {
    private Long studentId;
    private String studentName;
    private String studentEmail;
    private String studentPhone;

    private Long teacherId;
    private String teacherName;
    private String teacherEmail;
    private String teacherPhone;
}
