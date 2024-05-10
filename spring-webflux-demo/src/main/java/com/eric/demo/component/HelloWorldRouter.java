package com.eric.demo.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.RequestPredicates;
@Configuration
public class HelloWorldRouter {

    /**
     * @param helloWorldHandler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> routeHelloWorld(HelloWorldHandler helloWorldHandler){
        return RouterFunctions.route(RequestPredicates.GET("/helloWorld")
        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),helloWorldHandler::helloWorld);
    }
}
