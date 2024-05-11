package com.eric.demo.repository;

import java.math.BigInteger;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.eric.demo.models.Note;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NoteRepository extends ReactiveCrudRepository<Note, BigInteger>{
    Mono<Note> findByNoteId(BigInteger noteId);
    
    @Query("select * from notes where noteTitle like '%noteTitle%' ")
    Flux<Note> getNotesByTitle(String noteTitle);

}
