package com.poc.mapqueuelock.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.poc.mapqueuelock.model.Request;
import com.poc.mapqueuelock.repo.RequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        Request result = null;
        int pageNumber = 0;

        do {

            Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("id").ascending());
            Page<Request> page = this.repo.findAll(pageable);
            List<Request> pageRequests = page.getContent();

            // queue is empty
            if (pageRequests.isEmpty()) {
                return Optional.ofNullable(null);
            }

            // check items from this page
            for (int i = 0; i < pageRequests.size(); i++) {
                result = pageRequests.get(i);

                IMap<Integer, String> lockMap = this.lockMap();
                String marker = lockMap.get(result.getId());

                if (marker == null) {
                    lockMap.put(result.getId(), result.getEmail());
                    break;
                } else {
                    result = null;
                }
            }

            // will try next page
            if (result == null) {
                pageNumber++;
            }

        } while (result == null);

        return Optional.of(result);
    }

    @Override
    public void markAsFulfilled(int id) {

        IMap<Integer, String> lockMap = this.lockMap();
        if (lockMap.containsKey(id)) {
            this.repo.deleteById(id);
            this.lockMap().remove(id);
        }
    }

}