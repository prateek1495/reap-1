package com.prateek.reap.Repository;

import com.prateek.reap.Entity.User;
import com.prateek.reap.Entity.UserStarReceived;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStarReceivedRepository extends CrudRepository<UserStarReceived, Integer> {
    UserStarReceived findByUser(User receiverUser);
}
