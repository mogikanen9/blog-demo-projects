
package com.poc.mapqueuelock.repo;

import com.poc.mapqueuelock.model.Request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface RequestRepository extends PagingAndSortingRepository <Request, Integer> {

    @Override
    Page <Request> findAll(Pageable pageable);

    @Override
    void delete(Request entity);

    void deleteById(int id);

    Request findById(int id);

}