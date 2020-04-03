package com.poc.mapqueuelock;

import com.poc.mapqueuelock.model.Request;
import com.poc.mapqueuelock.repo.RequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MapQueueLockApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MapQueueLockApplication.class, args);
	}

	@Autowired
	private RequestRepository repository;

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 20; i++) {
			repository.save(new Request(-1, "John" + i, "Doe" + i, "john.doe" + i + "@qwe.com"));
		}

	}

}
