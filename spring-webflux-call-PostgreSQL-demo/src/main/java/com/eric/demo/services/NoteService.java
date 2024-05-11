package com.eric.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.eric.demo.models.Note;
import com.eric.demo.repository.NoteRepository;
import com.eric.demo.repository.StudentRepository;
import com.eric.demo.repository.TeacherRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Transactional
public class NoteService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private NoteRepository noteRepository;

    public Mono<Note> createNote(Note note) {
        return noteRepository.save(note);
    }

    public Flux<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Mono<Note> findById(Long noteId) {
        return noteRepository.findById(noteId);
    }

    public Mono<Note> updateNote(Long noteId, Note note) {
        return noteRepository.findById(noteId)
                .flatMap(dbNote -> {
                    dbNote.setNoteTitle(student.getNoteTitle());
                    dbNote.setNoteContent(student.getNoteContent());
                    dbNote.setNoteDate(student.getNoteDate());
                    return noteRepository.save(dbNote);
                });
    }
}
