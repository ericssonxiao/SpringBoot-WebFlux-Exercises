package com.eric.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eric.demo.models.Student;
import com.eric.demo.services.StudentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Student> create(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @GetMapping
    public Flux<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}")
    public Mono<ResponseEntity<Student>> getNoteById(@PathVariable Long studentId){
        Mono<Student> student = studentService.findById(studentId);
        return student.map(s -> ResponseEntity.ok(s)).defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{studentId}")
    public Mono<ResponseEntity<Student>> updateStudentById(@PathVariable Long studentId, @RequestBody Student student){
        return studentService.updateStudent(studentId, student)
        .map(updatedStudent -> ResponseEntity.ok(updatedStudent))
        .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
