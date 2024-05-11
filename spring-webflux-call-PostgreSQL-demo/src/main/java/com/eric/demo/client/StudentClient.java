package com.eric.demo.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.eric.demo.models.Student;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class StudentClient {

    private WebClient client = WebClient.create("http://localhost:8080");

    public Mono<Student> getStudent(Long studentId) {
        return client.get().uri("/students/{studentId}", studentId)
                .retrieve().bodyToMono(Student.class).log("Student fetched!");
    }

    public Flux<Student> getAllStudents() {
        return client.get().uri("/students")
                .exchangeToFlux(ClientResponse -> ClientResponse.bodyToFlux(Student.class))
                .log("Students Fetched!");
    }

    public Mono<Student> createStudent(Student student) {
        Mono<Student> studentMono = Mono.just(student);
        return client.post().uri("/students").contentType(MediaType.APPLICATION_JSON)
                .body(studentMono, Student.class).retrieve()
                .bodyToMono(Student.class).log("Created Student!");
    }
}
