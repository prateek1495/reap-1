package com.prateek.reap.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prateek.reap.util.DateTimeUtils;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonManagedReference
    @ManyToOne
    private User user;

    @ElementCollection
    private List<String> itemUrl=new ArrayList<>();

    @Column(nullable = true)
    private Integer quantity;

    @Column(nullable = false)
    private Integer totalPrice;

    @CreationTimestamp
    LocalDateTime orderDate;

    @Transient
    private String elapsedTime;

    public String getElapsedTime() {
        setElapsedTime(DateTimeUtils.get(getOrderDate()));
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(List<String> itemUrl) {
        this.itemUrl = itemUrl;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", itemUrl=" + itemUrl +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                ", elapsedTime='" + elapsedTime + '\'' +
                '}';
    }
}
