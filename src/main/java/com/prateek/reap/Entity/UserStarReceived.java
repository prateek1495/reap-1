package com.prateek.reap.Entity;

import javax.persistence.*;

@Entity
public class UserStarReceived {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    private Integer goldStarRecieved;

    private Integer silverStarRecieved;

    private Integer bronzeStarRecieved;

    public Integer getId() {
        return id;
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

    public Integer getGoldStarRecieved() {
        return goldStarRecieved;
    }

    public void setGoldStarRecieved(Integer goldStarRecieved) {
        this.goldStarRecieved = goldStarRecieved;
    }

    public Integer getSilverStarRecieved() {
        return silverStarRecieved;
    }

    public void setSilverStarRecieved(Integer silverStarRecieved) {
        this.silverStarRecieved = silverStarRecieved;
    }

    public Integer getBronzeStarRecieved() {
        return bronzeStarRecieved;
    }

    public void setBronzeStarRecieved(Integer bronzeStarRecieved) {
        this.bronzeStarRecieved = bronzeStarRecieved;
    }

    @Override
    public String toString() {
        return "UserStarReceived{" +
                "id=" + id +
                ", user=" + user +
                ", goldStarRecieved=" + goldStarRecieved +
                ", silverStarRecieved=" + silverStarRecieved +
                ", bronzeStarRecieved=" + bronzeStarRecieved +
                '}';
    }
}

