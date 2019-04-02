package com.prateek.reap.Repository;

import com.prateek.reap.Entity.User;
import com.prateek.reap.Entity.UserStarCount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStarCountRepository extends CrudRepository<UserStarCount, Integer> {
    UserStarCount findByUser(User senderUser);
}
