package com.eric.demo.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.eric.demo.models.Note;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NoteRepository extends ReactiveCrudRepository<Note, Long, String>{
    Mono<Note> findByNoteId(Long noteId);
    
    /**
     * @param noteTitle
     * @return
     */
    @Query("select * from notes where noteTitle like '%$2%' ")
    Flux<Note> getNotesByTitle(String noteTitle);

}
