package com.github.mogikanen9.demo.rest.client;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@RestController
public class FruitsController {

    @GetMapping("/v1/fruits/{name}")
    public Fruit get(@PathVariable String name) {
        //RestTemplate restTemplate = new RestTemplate();
        //Set<Fruit> quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Fruit.class);
        return new Fruit("Test name", "Test desc");
    }
}