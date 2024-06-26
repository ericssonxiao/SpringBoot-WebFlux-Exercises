package com.eric.demo.services;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eric.demo.models.Note;
import com.eric.demo.models.Student;
import com.eric.demo.repository.NoteRepository;
import com.eric.demo.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Mono<Student> createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Flux<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Mono<Student> findById(BigInteger studentId) {
        return studentRepository.findById(studentId);
    }

    public Mono<Student> updateStudent(BigInteger studentId, Student student) {
        return studentRepository.findById(studentId)
                .flatMap(dbStudent -> {
                    dbStudent.setStudentName(student.getStudentName());
                    dbStudent.setStudentEmail(student.getStudentEmail());
                    dbStudent.setStudentPhone(student.getStudentPhone());
                    return studentRepository.save(dbStudent);
                });
    }

    public Mono<Student> deleteStudent(BigInteger studentId){
        return studentRepository.findById(studentId)
        .flatMap(existingStudent -> studentRepository.delete(existingStudent)
        .then(Mono.just(existingStudent)));
    }

    public Flux<Note> findAllNotesByStudentId(BigInteger studentId){
        return studentRepository.getAllNotesByStudentId(studentId);
    }

    // public Flux<Student> fetchStudents(List<BigInteger> studentIds){
    //     return Flux.fromIterable(studentIds)
    //     .parallel()
    //     .runOn(Schedulers.boundedElastic())
    //     .flatMap(i -> findById(i))
    //     .ordered((s1,s2) -> s2.getStudentId() - s1.getStudentId());
    // }
}
