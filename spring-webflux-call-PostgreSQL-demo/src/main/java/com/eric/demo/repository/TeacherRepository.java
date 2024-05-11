package com.eric.demo.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.eric.demo.models.Note;
import com.eric.demo.models.Teacher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeacherRepository extends ReactiveCrudRepository<Teacher,Long>{
    Mono<Teacher> findByTeacherId(Long teacherId);

    /**
     * @param teacherId
     * @return
     */
    @Query("select * from notes where teacher_id == $1")
    Flux<Note> getAllNotesByTeacherId(Long teacherId);
}
