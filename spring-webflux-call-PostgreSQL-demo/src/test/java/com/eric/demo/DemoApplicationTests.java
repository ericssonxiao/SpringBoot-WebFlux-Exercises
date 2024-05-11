package com.eric.demo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void contextLoads() {
		webTestClient.get().uri("/helloWorld")
		.accept(MediaType.TEXT_PLAIN)
		.exchange()
		.expectStatus().isOk()
		.expectBody(String.class).isEqualTo("Hello World!");
	}

}
