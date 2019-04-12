package com.prateek.reap.util;


import com.prateek.reap.entity.User;
import com.prateek.reap.entity.UserPrincipal;
import org.springframework.security.core.Authentication;


public class CommonUtils {

    public static User currentLoggedInUser(Authentication authentication) {
        User user = ((UserPrincipal) authentication.getPrincipal()).getUser();
        return user;
    }

}
