/*
package com.prateek.reap.Entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    private List<Item>items;

    private User user;

    private Integer totalPrice;

    private Integer quantity;

    @CreationTimestamp
    private LocalDateTime orderDate;
}
*/
