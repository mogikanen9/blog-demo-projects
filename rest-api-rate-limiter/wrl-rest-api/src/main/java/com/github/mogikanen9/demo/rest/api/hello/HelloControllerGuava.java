package com.github.mogikanen9.demo.rest.api.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloControllerGuava {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/say")
    public String sayHello(@RequestParam("name") String name) {
        return String.format("Hello, %s", name);
    }
}