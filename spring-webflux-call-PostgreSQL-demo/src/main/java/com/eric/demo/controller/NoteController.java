package com.eric.demo.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.eric.demo.models.Note;
import com.eric.demo.services.NoteService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/notes")
public class NoteController {
    
    @Autowired
    private NoteService noteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Note> create(@RequestBody Note note){
        return noteService.createNote(note);
    }

    @GetMapping
    public Flux<Note> getAllNotes(){
        return noteService.getAllNotes();
    }

    @GetMapping("/{noteId}")
    public Mono<ResponseEntity<Note>> getNoteById(@PathVariable BigInteger noteId){
        Mono<Note> note = noteService.findById(noteId);
        return note.map(n -> ResponseEntity.ok(n)).defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{noteId}")
    public Mono<ResponseEntity<Note>> updateNoteById(@PathVariable BigInteger noteId, @RequestBody Note note){
        return noteService.updateNote(noteId, note)
        .map(updatedNote -> ResponseEntity.ok(updatedNote))
        .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

}
