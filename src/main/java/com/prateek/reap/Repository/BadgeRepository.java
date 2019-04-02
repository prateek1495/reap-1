package com.prateek.reap.Repository;

import com.prateek.reap.Entity.BadgesGiven;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends CrudRepository<BadgesGiven, Integer> {
    List<BadgesGiven> findByFlag(boolean flag);
}