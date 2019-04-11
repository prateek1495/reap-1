package com.prateek.reap.repository;

import com.prateek.reap.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {

//    Order findByUser_Id(Integer id);
}
