package com.eric.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eric.demo.client.TeacherClient;
import com.eric.demo.models.Student;
import com.eric.demo.models.Teacher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client/teachers")
public class TeacherClientController {

    @Autowired
    private TeacherClient teacherClient;

    @GetMapping("/teacherId")
    public Mono<ResponseEntity<Teacher>> getTeacherById(@PathVariable Long teacherId) {
        Mono<Teacher> teacher = teacherClient.getTeacher(teacherId);
        return teacher.map(t -> ResponseEntity.ok(t))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<Teacher> getAllTeachers(){
        return teacherClient.getAllTeachers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Teacher> create(@PathVariable Teacher teacher){
        return teacherClient.createTeacher(teacher);
    }
}
