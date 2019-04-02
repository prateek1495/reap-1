package com.prateek.reap.Repository;


import com.prateek.reap.Entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    UserRole findByName(String name);

}
