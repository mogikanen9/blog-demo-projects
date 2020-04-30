
package com.mogikanensoftware.greetings.client;

import com.mogikanensoftware.greetings.client.service.GreetingService;
import com.mogikanensoftware.greetings.client.service.GreetingServiceRestImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);

		String idToken = System.getProperty("MY_CLIENT_ID_TOKEN");
		log.info("MY_CLIENT_ID_TOKEN->{}", idToken);

		GreetingService gs = new GreetingServiceRestImpl("http://localhost:8082",idToken);

		log.info("Say hi -> {}", gs.hi());
		log.info("Say hello -> {}", gs.hello("Huinea"));

		System.exit(0);

	}

}
