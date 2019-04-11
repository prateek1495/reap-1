package com.prateek.reap.service;

import com.prateek.reap.entity.Order;
import com.prateek.reap.entity.User;
import com.prateek.reap.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SignUpService signUpService;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public String redeemPoints(String items, String totalPrice, String itemUrls, User user) {
        Integer cartPrice = Integer.parseInt(totalPrice);
        if (cartPrice > user.getPoints()) {
            return "Not enough points";
        }
        if (cartPrice == 0) {
            return "Order something";
        }
        else {
            String itemArray[] = items.split(" ");
            String itemUrl[] = itemUrls.split(" ");
            Order order = new Order();
            order.setItemUrl(Arrays.asList(itemArray));
            order.setTotalPrice(cartPrice);
            order.setUser(user);
            user.setPoints(user.getPoints() - cartPrice);
            signUpService.saveUser(user);
            saveOrder(order);
        }
        return "redeemed";

    }



    public List<Order> findAllOrders(Integer id) {
        Optional<User> user = signUpService.findById(id);
        return orderRepository.findByUser(new Sort(Sort.Direction.DESC,"orderDate"),user.get());
    }



   /* public Order findByUserId(Integer id) {

        return orderRepository.findByUser_Id(id);
    }*/
}
