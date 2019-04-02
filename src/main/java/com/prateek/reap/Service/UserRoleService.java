package com.prateek.reap.Service;

import com.prateek.reap.Entity.UserRole;
import com.prateek.reap.Repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    public UserRole checkByName(String name) {
        return userRoleRepository.findByName(name);
    }

    public Iterable<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    public void save(UserRole role) {
        userRoleRepository.save(role);
    }

    public Optional<UserRole> findById(int roleId) {
        return userRoleRepository.findById(roleId);
    }
}
