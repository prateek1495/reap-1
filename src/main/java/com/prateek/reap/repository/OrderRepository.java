package com.prateek.reap.repository;

import com.prateek.reap.entity.Order;
import com.prateek.reap.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {
    List<Order> findByUser(Sort sort, User user);

//    Order findByUser_Id(Integer id);
}
