package com.prateek.reap.repository;

import com.prateek.reap.entity.BadgesGiven;
import com.prateek.reap.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BadgeRepository extends CrudRepository<BadgesGiven, Integer> {

    List<BadgesGiven> findByGiverAndFlag(Sort sort,User user,Boolean flag);

    List<BadgesGiven> findByReceiverAndFlag(Sort sort, User user, Boolean flag);

    List<BadgesGiven> findAllByGiverOrReceiver(Sort sort,User user, User user1);

    List<BadgesGiven> findAllByFlag(Sort sort,Boolean flag);

    List<BadgesGiven> findByReceiverFirstNameLike(String name);

    List<BadgesGiven> findAllByUpdatedAtBetween(Sort sort,LocalDateTime startDate, LocalDateTime endDate);

}