package com.prateek.reap.repository;


import com.prateek.reap.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    UserRole findByName(String name);

    UserRole findByPriority(int revokePriority);
}
