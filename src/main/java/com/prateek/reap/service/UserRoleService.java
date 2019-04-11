package com.prateek.reap.service;

import com.prateek.reap.entity.UserRole;
import com.prateek.reap.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

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

    public UserRole findByPriority(int revokePriority) {
        return userRoleRepository.findByPriority(revokePriority);
    }
}
