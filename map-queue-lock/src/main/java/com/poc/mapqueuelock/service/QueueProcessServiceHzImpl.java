package com.poc.mapqueuelock.service;

import com.poc.mapqueuelock.model.Request;

import org.springframework.stereotype.Service;

@Service
public class QueueProcessServiceHzImpl implements QueueProcessService {

    @Override
    public Request getNextAvailable() {
        // TODO Auto-generated method stub
        
        return new Request();
    }

}