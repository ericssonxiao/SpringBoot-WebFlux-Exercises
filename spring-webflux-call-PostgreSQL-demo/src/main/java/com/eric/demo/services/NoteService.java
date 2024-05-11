package com.eric.demo.services;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eric.demo.models.Note;
import com.eric.demo.repository.NoteRepository;
import com.eric.demo.repository.StudentRepository;
import com.eric.demo.repository.TeacherRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Transactional
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Mono<Note> createNote(Note note) {
        return noteRepository.save(note);
    }

    public Flux<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Mono<Note> findById(BigInteger noteId) {
        return noteRepository.findById(noteId);
    }

    public Mono<Note> updateNote(BigInteger noteId, Note note) {
        return noteRepository.findById(noteId)
                .flatMap(dbNote -> {
                    dbNote.setNoteTitle(note.getNoteTitle());
                    dbNote.setNoteContent(note.getNoteContent());
                    dbNote.setNoteDate(note.getNoteDate());
                    return noteRepository.save(dbNote);
                });
    }
}
