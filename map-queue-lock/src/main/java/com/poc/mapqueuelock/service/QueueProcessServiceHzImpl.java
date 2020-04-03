package com.poc.mapqueuelock.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.poc.mapqueuelock.model.Request;
import com.poc.mapqueuelock.repo.RequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class QueueProcessServiceHzImpl implements QueueProcessService {

    private final RequestRepository repo;

    private HazelcastInstance hzInstance;

    public QueueProcessServiceHzImpl(@Autowired final RequestRepository repo, @Autowired HazelcastInstance hzInstance) {
        this.repo = repo;
        this.hzInstance = hzInstance;
    }

    protected IMap<Integer, String> lockMap() {
        return hzInstance.getMap("recordsInReview");
    }

    @Override
    public Optional<Request> getNextAvailable() {

        Optional<Request> result = null;

        do {
            result = this.repo.findFirstByOrderByIdAsc();

            if (!result.isPresent()) {
                return result;
            }

            IMap<Integer, String> lockMap = this.lockMap();
            String marker = lockMap.get(result.get().getId());

            if (marker == null) {
                lockMap.put(result.get().getId(), result.get().getEmail());
                break;
            }
        } while (result != null);

        return result;
    }

    @Override
    public void markAsFulfilled(int id) {
        this.repo.deleteById(id);
        this.lockMap().remove(id);
    }

}