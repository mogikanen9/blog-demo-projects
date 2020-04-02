package com.poc.mapqueuelock.controller;

import com.poc.mapqueuelock.model.Request;
import com.poc.mapqueuelock.service.QueueProcessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/request")
public class RequestController {

    private QueueProcessService service;

    public RequestController(@Autowired QueueProcessService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Request> nextRecord() {
        return new ResponseEntity<>(this.service.getNextAvailable(), HttpStatus.OK);
    }

}