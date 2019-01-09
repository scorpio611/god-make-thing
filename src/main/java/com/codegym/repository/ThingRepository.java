package com.codegym.repository;

import com.codegym.model.Thing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ThingRepository extends PagingAndSortingRepository<Thing, Long> {
    Iterable<Thing> findAllByName(Thing thing);

    Page<Thing> findAllByDescriptionContaining(String description, Pageable pageable);
}
