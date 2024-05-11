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

import com.eric.demo.client.NoteClient;
import com.eric.demo.models.Note;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client/notes")
public class NoteClientController {

    @Autowired
    private NoteClient noteClient;

    @GetMapping("/noteId")
    public Mono<ResponseEntity<Note>> getNoteById(@PathVariable Long noteId) {
        Mono<Note> note = noteClient.getNote(noteId);
        return note.map(n -> ResponseEntity.ok(n))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<Note> getAllNotes(){
        return noteClient.getAllNotes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Note> create(@PathVariable Note note){
        return noteClient.createNote(note);
    }
}
