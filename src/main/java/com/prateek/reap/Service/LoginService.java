package com.prateek.reap.Service;

import com.prateek.reap.Entity.User;
import com.prateek.reap.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;


    public Optional<User> validateUser(User responseData) {

        Optional<User> loginStatus = loginRepository.findByEmailAndPassword(responseData.getEmail(), responseData.getPassword());

        return loginStatus;

    }

    public Optional<User> find(int userId) {
        Optional<User> id = loginRepository.findById(userId);
        return id;
    }

    /*public User findUserByResetToken(String resetToken) {
        return loginRepository.findByToken(resetToken);
    }*/

    public User findUserByEmail(String email) {
        return loginRepository.findByEmail(email);
    }


}
