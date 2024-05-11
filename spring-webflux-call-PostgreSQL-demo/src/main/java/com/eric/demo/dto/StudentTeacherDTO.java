package com.eric.demo.dto;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentTeacherDTO {
    private BigInteger studentId;
    private String studentName;
    private String studentEmail;
    private String studentPhone;

    private BigInteger teacherId;
    private String teacherName;
    private String teacherEmail;
    private String teacherPhone;
}
