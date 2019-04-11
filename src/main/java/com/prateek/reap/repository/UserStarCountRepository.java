package com.prateek.reap.repository;

import com.prateek.reap.entity.User;
import com.prateek.reap.entity.UserStarCount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStarCountRepository extends CrudRepository<UserStarCount, Integer> {
    UserStarCount findByUser(User senderUser);
    UserStarCount findByUser_Id(Integer id);
}
