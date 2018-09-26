package com.example.demo.store.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.store.domain.Store;

public interface StoreRepository extends PagingAndSortingRepository<Store, Long> {

}
