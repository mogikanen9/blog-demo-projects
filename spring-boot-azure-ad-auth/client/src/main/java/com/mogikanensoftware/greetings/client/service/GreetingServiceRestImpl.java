
package com.mogikanensoftware.greetings.client.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GreetingServiceRestImpl implements GreetingService {

    private String restServiceUrl;

    private RestTemplate restTemplate;

    private String idToken;

    public GreetingServiceRestImpl(String restServiceUrl, String idToken) {
        this.restServiceUrl = restServiceUrl;
        this.restTemplate = new RestTemplate();
        this.idToken = idToken;
    }

    @Override
    public String hi() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+this.idToken);

        String url = String.format("%s%s", restServiceUrl, "/api/v1/hi");
        HttpEntity<String> requestEntity = new HttpEntity<String>("",headers);

        ResponseEntity <String> rs = this.restTemplate.exchange(url, HttpMethod.GET,
         requestEntity, String.class);

        if (rs.getStatusCode().is2xxSuccessful()) {
            return rs.getBody();
        } else {
            return "Ups! Smth went wrong";
        }
    }

    @Override
    public String hello(String name) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+this.idToken);

        String url = String.format("%s%s?name=%s", restServiceUrl, "/api/v1/hello",name);
        HttpEntity<String> requestEntity = new HttpEntity<String>("",headers);

        ResponseEntity <String> rs = this.restTemplate.exchange(url, HttpMethod.GET,
         requestEntity, String.class);

        //ResponseEntity <String> rs = this.restTemplate.getForEntity(String.format("%s%s?name=%s", restServiceUrl, "/api/v1/hello",name), String.class);
        if (rs.getStatusCode().is2xxSuccessful()) {
            return rs.getBody();
        } else {
            return "Ups! Smth went wrong";
        }

    }

}