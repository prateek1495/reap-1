package com.prateek.reap.repository;

import com.prateek.reap.entity.Star;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends CrudRepository<Star,Integer> {
    Star findByName(String name);
}

