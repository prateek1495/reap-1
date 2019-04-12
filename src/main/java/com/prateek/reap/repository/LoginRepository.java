package com.prateek.reap.repository;

import com.prateek.reap.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends CrudRepository<User, Integer> {
    User findByEmailAndActive(String email,Boolean active);
}