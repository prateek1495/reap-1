package com.prateek.reap.repository;

import com.prateek.reap.entity.User;
import com.prateek.reap.entity.UserStarReceived;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStarReceivedRepository extends CrudRepository<UserStarReceived, Integer> {
    UserStarReceived findByUser(User receiverUser);

    UserStarReceived findByUser_Id(Integer id);
    @Query(value = "Select * from user u join user_star_received ur on u.id=ur.user_id  order by u.points DESC limit 5",nativeQuery = true)
    List<UserStarReceived> findByTopNewers();
}
