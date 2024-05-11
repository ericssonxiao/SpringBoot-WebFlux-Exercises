package com.eric.demo.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eric.demo.client.StudentClient;
import com.eric.demo.models.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client/students")
public class StudentClientController {

    @Autowired
    private StudentClient studentClient;

    @GetMapping("/studentId")
    public Mono<ResponseEntity<Student>> getStudentById(@PathVariable BigInteger studentId) {
        Mono<Student> student = studentClient.getStudent(studentId);
        return student.map(s -> ResponseEntity.ok(s))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<Student> getAllStudents(){
        return studentClient.getAllStudents();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Student> create(@PathVariable Student student){
        return studentClient.createStudent(student);
    }
}
