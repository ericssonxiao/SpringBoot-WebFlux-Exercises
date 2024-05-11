package com.eric.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.eric.demo.models.Note;
import com.eric.demo.models.Student;
import com.eric.demo.models.Teacher;
import com.eric.demo.repository.NoteRepository;
import com.eric.demo.repository.StudentRepository;
import com.eric.demo.repository.TeacherRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Transactional
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private NoteRepository noteRepository;

    public Mono<Teacher> createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Flux<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Mono<Teacher> findById(Long teacherId) {
        return teacherRepository.findById(teacherId);
    }

    public Mono<Teacher> updateTeacher(Long teacherId, Teacher teacher) {
        return teacherRepository.findById(teacherId)
                .flatMap(dbTeacher -> {
                    dbTeacher.setTeacherName(teacher.getTeacherName());
                    dbTeacher.setTeacherEmail(teacher.getTeacherEmail());
                    dbTeacher.setTeacherPhone(teacher.getTeacherPhone());
                    return teacherRepository.save(dbTeacher);
                });
    }

    public Mono<Teacher> deleteTeacher(Long teacherId){
        return teacherRepository.findById(teacherId)
        .flatMap(existingTeacher -> teacherRepository.delete(existingTeacher)
        .then(Mono.just(existingTeacher)));
    }

    public Flux<Note> findAllNotesByTeacherId(Long teacherId){
        return teacherRepository.getAllNotesByTeacherId(teacherId);
    }
}
