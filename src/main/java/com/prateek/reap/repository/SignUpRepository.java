package com.prateek.reap.repository;

import com.prateek.reap.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignUpRepository extends CrudRepository<User, Integer> {

    List<User> findByEmailAndActive(String email, boolean active);

    User findByEmail(String email);

    User findByToken(String resetToken);




}
