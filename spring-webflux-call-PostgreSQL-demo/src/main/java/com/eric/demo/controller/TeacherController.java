package com.eric.demo.controller;

import java.math.BigInteger;

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

import com.eric.demo.models.Teacher;
import com.eric.demo.services.TeacherService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    
    @Autowired
    private TeacherService teacherService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Teacher> create(@RequestBody Teacher teacher){
        return teacherService.createTeacher(teacher);
    }

    @GetMapping
    public Flux<Teacher> getAllTeachers(){
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{teacherId}")
    public Mono<ResponseEntity<Teacher>> getTeacherById(@PathVariable BigInteger teacherId){
        Mono<Teacher> teacher = teacherService.findById(teacherId);
        return teacher.map(t -> ResponseEntity.ok(t)).defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{teacherId}")
    public Mono<ResponseEntity<Teacher>> updateNoteById(@PathVariable BigInteger teacherId, @RequestBody Teacher teacher){
        return teacherService.updateTeacher(teacherId, teacher)
        .map(updatedTeacher -> ResponseEntity.ok(updatedTeacher))
        .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
