package com.prateek.reap.Repository;

import com.prateek.reap.Entity.Star;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends CrudRepository<Star,Integer> {
    Star findByName(String name);
}

