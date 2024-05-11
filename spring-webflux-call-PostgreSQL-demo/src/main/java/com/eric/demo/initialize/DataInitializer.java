package com.eric.demo.initialize;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.eric.demo.models.Note;
import com.eric.demo.models.Student;
import com.eric.demo.models.Teacher;
import com.eric.demo.repository.NoteRepository;
import com.eric.demo.repository.StudentRepository;
import com.eric.demo.repository.TeacherRepository;

import java.math.BigInteger;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
@Profile("!test")
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void run(String... args) {
        initialDataSetup();
    }

    private List<Student> getStudents() {
        return Arrays.asList(new Student(null, "Tom", "tom@gmail.com", "4381234567"),
                new Student(null, "Jack", "jack@gmail.com", "1234567890"),
                new Student(null, "Smith", "smith@gmail.com", "1234567890"));
    }

    private List<Teacher> getTeachers() {
        return Arrays.asList(new Teacher(null, "Tea01", "tea01@gmail.com", "2341231234"),
                new Teacher(null, "Tea02", "tea02@gmail.com", "4567891234"),
                new Teacher(null, "Tea03", "tea03@gmail.com", "4567891234"));
    }

    private List<Note> getNotes() {
        return Arrays.asList(
                new Note(null, "Note title 01", "note content 01", LocalDate.now(), new BigInteger("1"),
                        new BigInteger("1")),
                new Note(null, "Note title 02", "note content 02", LocalDate.now(), new BigInteger("2"),
                        new BigInteger("2")),
                new Note(null, "Note title 03", "note content 03", LocalDate.now(), new BigInteger("3"),
                        new BigInteger("3")));
    }

    private void initialDataSetup() {

        studentRepository.deleteAll()
                .thenMany(Flux.fromIterable(getStudents()))
                .flatMap(studentRepository::save)
                .thenMany(studentRepository.findAll())
                .subscribe(student -> {
                    log.info("Student Data Inserted from CommandLineRunner " + student);
                });

        teacherRepository.deleteAll()
                .thenMany(Flux.fromIterable(getTeachers()))
                .flatMap(teacherRepository::save)
                .thenMany(teacherRepository.findAll())
                .subscribe(teacher -> {
                    log.info("Teacher Data Inserted from CommandLineRunner " + teacher);
                });

        noteRepository.deleteAll()
                .thenMany(Flux.fromIterable(getNotes()))
                .flatMap(noteRepository::save)
                .thenMany(noteRepository.findAll())
                .subscribe(note -> {
                    log.info("Notes Data Inserted from CommandLineRunner " + note);
                });
    }

}
