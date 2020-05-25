
package com.mogikanensoftware.azure.profilereceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ProfileReceiverApplication implements CommandLineRunner{


	@Autowired
	private ProfileConsumer consumer;

	public static void main(final String[] args) {
		SpringApplication.run(ProfileReceiverApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		log.info("Hello...");
		//consumer.consume();
		
	}

}
