package com.prateek.reap.Service;

import com.prateek.reap.Entity.Item;
import com.prateek.reap.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    public List<Item> findAll() {
        return (List<Item>) itemRepository.findAll();
    }
}
