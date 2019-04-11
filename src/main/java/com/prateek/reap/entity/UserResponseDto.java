package com.prateek.reap.entity;

public class UserResponseDto {

    private User user;

    private Iterable<UserRole> allRoles;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Iterable<UserRole> getAllRoles() {
        return allRoles;
    }

    public void setAllRoles(Iterable<UserRole> allRoles) {
        this.allRoles = allRoles;
    }


    @Override
    public String toString() {
        return "UserResponseDto{" +
                "user=" + user +
                ", allRoles=" + allRoles +
                '}';
    }

}
