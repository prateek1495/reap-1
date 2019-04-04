package com.prateek.reap.Repository;

import com.prateek.reap.Entity.BadgesGiven;
import com.prateek.reap.Entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends CrudRepository<BadgesGiven, Integer> {
    List<BadgesGiven> findByFlag(boolean flag);

    List<BadgesGiven> findByGiver(User user);

    List<BadgesGiven> findByReceiver(User user);

    List<BadgesGiven> findAllByGiverOrReceiver(User user, User user1);

    List<BadgesGiven> findAll(Sort sort);

    List<BadgesGiven> findByReceiverFirstNameLike(String name);

}