package com.poc.mapqueuelock.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.poc.mapqueuelock.model.Request;
import com.poc.mapqueuelock.repo.RequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class QueueProcessServiceHzImpl implements QueueProcessService {

    private final RequestRepository repo;

    public QueueProcessServiceHzImpl(@Autowired final RequestRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Request> getNextAvailable() {

        return this.repo.findFirstByOrderByIdAsc();
    }

    @Override
    public void markAsFulfilled(int id) {
        this.repo.deleteById(id);
    }

}