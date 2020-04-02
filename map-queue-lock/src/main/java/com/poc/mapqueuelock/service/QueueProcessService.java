package com.poc.mapqueuelock.service;

import com.poc.mapqueuelock.model.Request;

public interface QueueProcessService {

    Request getNextAvailable();
}