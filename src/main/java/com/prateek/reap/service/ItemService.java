/*
package com.prateek.reap.service;

import com.prateek.reap.entity.Item;
import com.prateek.reap.entity.Order;
import com.prateek.reap.entity.UserStarReceived;
import com.prateek.reap.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAll() {
        return (List<Item>) itemRepository.findAll();
    }

    public void save(Item item1) {
        itemRepository.save(item1);
    }

    public Order findByUserId(Integer id) {

        return itemRepository.findByUser_Id(id);
    }
}
*/
