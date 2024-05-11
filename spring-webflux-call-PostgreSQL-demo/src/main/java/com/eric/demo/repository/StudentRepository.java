package com.eric.demo.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.eric.demo.models.Note;
import com.eric.demo.models.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveCrudRepository<Student, Long>{
    Mono<Student> findByStudentId(Long studentId);
    
    /**
     * @param studentId
     * @return
     */
    @Query("select * from notes where student_id == $1")
    Flux<Note> getAllNotesByStudentId(Long studentId);
}
