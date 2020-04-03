package com.poc.mapqueuelock.service;

import java.util.Optional;

import com.poc.mapqueuelock.model.Request;

public interface QueueProcessService {

    Optional<Request> getNextAvailable();

    void markAsFulfilled(int id);
}