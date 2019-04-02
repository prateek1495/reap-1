package com.prateek.reap.Repository;

import com.prateek.reap.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SignUpRepository extends CrudRepository<User, Integer> {
    /*List<User> findByEmail(String responseData);*/
    List<User> findByEmailAndActive(String email, boolean active);

    User findByEmail(String email);

    User findByToken(String resetToken);


}
