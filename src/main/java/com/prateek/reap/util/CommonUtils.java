package com.prateek.reap.util;


import com.prateek.reap.Entity.User;
import com.prateek.reap.Entity.UserPrincipal;
import com.prateek.reap.Entity.UserRole;
import org.springframework.security.core.Authentication;
import java.util.Comparator;
import java.util.Set;


public class CommonUtils {

    public static String getFullName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    public static UserRole getHighestRolePriority(Set<UserRole> currentRoles) {
        UserRole userRole = currentRoles.stream().max(Comparator.comparing(UserRole::getPriority)).get();
        return userRole;
    }

    public static User currentLoggedInUser(Authentication authentication) {
        User user = ((UserPrincipal) authentication.getPrincipal()).getUser();
        return user;
    }
}
