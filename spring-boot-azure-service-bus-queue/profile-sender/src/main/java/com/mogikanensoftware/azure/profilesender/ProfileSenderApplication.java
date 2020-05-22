package com.mogikanensoftware.azure.profilesender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ProfileSenderApplication implements CommandLineRunner{


	@Autowired
	private ProfileProducer producer;

	public static void main(String[] args) {
		SpringApplication.run(ProfileSenderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Hello...");
		producer.produce();
		
	}

}
