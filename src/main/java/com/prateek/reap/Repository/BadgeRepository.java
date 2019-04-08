package com.prateek.reap.Repository;

import com.prateek.reap.Entity.BadgesGiven;
import com.prateek.reap.Entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BadgeRepository extends CrudRepository<BadgesGiven, Integer> {
    List<BadgesGiven> findByFlag(boolean flag);

    List<BadgesGiven> findByGiver(Sort sort,User user);

    List<BadgesGiven> findByReceiver(Sort sort,User user);

    List<BadgesGiven> findAllByGiverOrReceiver(Sort sort,User user, User user1);

    List<BadgesGiven> findAll(Sort sort);

    List<BadgesGiven> findByReceiverFirstNameLike(String name);

    List<BadgesGiven> findAllByUpdatedAtBetweenOrderByUpdatedAtDesc(LocalDateTime startDate, LocalDateTime endDate);
}