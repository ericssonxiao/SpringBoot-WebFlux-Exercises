package com.eric.demo.client;

import java.math.BigInteger;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.eric.demo.models.Teacher;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class TeacherClient {
    private WebClient client = WebClient.create("http://localhost:8080");

    public Mono<Teacher> getTeacher(BigInteger teacherId) {
        return client.get().uri("/teachers/{teacherId}", teacherId).retrieve()
                .bodyToMono(Teacher.class).log("Teacher fetched!");
    }

    public Flux<Teacher> getAllTeachers() {
        return client.get().uri("/teachers")
                .exchangeToFlux(ClientResponse -> ClientResponse.bodyToFlux(Teacher.class))
                .log("Teachers fetched!");
    }

    public Mono<Teacher> createTeacher(Teacher teacher) {
        Mono<Teacher> teacherMono = Mono.just(teacher);
        return client.post().uri("/teachers").contentType(MediaType.APPLICATION_JSON)
                .body(teacherMono, Teacher.class).retrieve()
                .bodyToMono(Teacher.class).log("Created Teacher!");
    }

}
