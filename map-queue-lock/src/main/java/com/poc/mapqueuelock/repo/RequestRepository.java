
package com.poc.mapqueuelock.repo;

import java.util.Optional;

import com.poc.mapqueuelock.model.Request;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface RequestRepository extends PagingAndSortingRepository<Request, Integer> {

    Optional<Request> findFirstByOrderByIdAsc();
}