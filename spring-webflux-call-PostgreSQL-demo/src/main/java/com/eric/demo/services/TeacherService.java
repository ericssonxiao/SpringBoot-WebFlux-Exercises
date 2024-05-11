package com.eric.demo.services;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eric.demo.models.Note;
import com.eric.demo.models.Teacher;
import com.eric.demo.repository.NoteRepository;
import com.eric.demo.repository.TeacherRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Transactional
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    
    public Mono<Teacher> createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Flux<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Mono<Teacher> findById(BigInteger teacherId) {
        return teacherRepository.findById(teacherId);
    }

    public Mono<Teacher> updateTeacher(BigInteger teacherId, Teacher teacher) {
        return teacherRepository.findById(teacherId)
                .flatMap(dbTeacher -> {
                    dbTeacher.setTeacherName(teacher.getTeacherName());
                    dbTeacher.setTeacherEmail(teacher.getTeacherEmail());
                    dbTeacher.setTeacherPhone(teacher.getTeacherPhone());
                    return teacherRepository.save(dbTeacher);
                });
    }

    public Mono<Teacher> deleteTeacher(BigInteger teacherId) {
        return teacherRepository.findById(teacherId)
                .flatMap(existingTeacher -> teacherRepository.delete(existingTeacher)
                        .then(Mono.just(existingTeacher)));
    }

    public Flux<Note> findAllNotesByTeacherId(BigInteger teacherId) {
        return teacherRepository.getAllNotesByTeacherId(teacherId);
    }
}
