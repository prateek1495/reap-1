package com.prateek.reap.service;

import com.prateek.reap.entity.Order;
import com.prateek.reap.entity.User;
import com.prateek.reap.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SignUpService signUpService;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public String redeemPoints(String items, Integer cartPrice, String itemUrls, User user) {
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

    public Order findByUserId(Integer id) {

        return orderRepository.findByUser_Id(id);
    }
}
