package com.eric.demo.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.eric.demo.models.Note;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class NoteClient {

    private WebClient client = WebClient.create("http://localhost:8080");

    public Mono<Note> getNote(Long noteId) {
        return client.get().uri("/notes/{noteId}", noteId)
                .retrieve().bodyToMono(Note.class)
                .log("note fetched!");
    }

    public Flux<Note> getAllNotes() {
        return client.get().uri("/notes")
                .exchangeToFlux(ClientResponse -> ClientResponse.bodyToFlux(Note.class))
                .log("Notes fetched!");
    }

    public Mono<Note> createNote(Note note) {
        Mono<Note> noteMono = Mono.just(note);
        return client.post().uri("/notes").contentType(MediaType.APPLICATION_JSON)
                .body(noteMono, Note.class).retrieve()
                .bodyToMono(Note.class).log("Create Note!");
    }

}
