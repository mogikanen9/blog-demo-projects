package com.github.mogikanen9.demo.rest.api.hello;

import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greeting")
public class HelloControllerResilience4J {

    @GetMapping("/hello/{name}")
    public ResponseEntity<String> hello(@PathVariable String name) {

        return new ResponseEntity<>(helloSupplier.apply(name), HttpStatus.OK);
    }

    Function<String, String> helloSupplier = (param) -> {
        // emulate some work
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.format("Hello, %s!", param);
    };
}