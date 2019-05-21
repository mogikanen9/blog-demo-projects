package com.github.mogikanen9.demo.rest.api.hello.wrlrestapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ExecutorService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloControllerGuavaTest{

	@Autowired
	private TestRestTemplate restTemplate;

    @Test
    public void testPing(){
        String body = this.restTemplate.getForObject("/api/hello/ping", String.class);
		assertThat(body).isEqualTo("pong");
    }

    @Test
    public void testPing5RPS(){
        //ExecutorService eService = 
        String body = this.restTemplate.getForObject("/api/hello/ping", String.class);
		assertThat(body).isEqualTo("pong");
    }
}