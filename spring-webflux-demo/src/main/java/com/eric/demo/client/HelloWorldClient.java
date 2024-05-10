package com.eric.demo.client;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class HelloWorldClient {
    public static void main(String[] args) {
        
        WebClient client = WebClient.create("https://localhost:8080");
        Mono<ClientResponse> result = client.get().uri("/helloWorld").accept(MediaType.TEXT_PLAIN).exchange();

    }
}
