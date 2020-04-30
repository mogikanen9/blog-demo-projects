
package com.mogikanensoftware.greetings.api.rest;

import java.security.Principal;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.security.oauth2.Jwt;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class GreetingController {

    @GetMapping("/whoami")
	public String whoami(OAuth2Authentication authentication) {
        log.info("authentication -> {}", authentication);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("principal -> {}", principal);
		return (String)principal;
    }

    @GetMapping("/hi")
    public String hi(final Principal principal) {
        log.info("hi endpoint is beeing called and principal is -> {}", principal);
        return "Hi";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = true)
    final String name) {
        log.info("hello endpoint is beeing called with name param {}", name);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("hello#principal -> {}", principal);

        return String.format("Hello, %s", name);
    }
}