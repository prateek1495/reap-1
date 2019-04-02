package com.prateek.reap.Entity;

import javax.persistence.*;

@Entity
public class UserStarCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    private Integer goldStarCount;

    private Integer silverStarCount;

    private Integer bronzeStarCount;

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

    public Integer getGoldStarCount() {
        return goldStarCount;
    }

    public void setGoldStarCount(Integer goldStarCount) {
        this.goldStarCount = goldStarCount;
    }

    public Integer getSilverStarCount() {
        return silverStarCount;
    }

    public void setSilverStarCount(Integer silverStarCount) {
        this.silverStarCount = silverStarCount;
    }

    public Integer getBronzeStarCount() {
        return bronzeStarCount;
    }

    public void setBronzeStarCount(Integer bronzeStarCount) {
        this.bronzeStarCount = bronzeStarCount;
    }

    @Override
    public String toString() {
        return "UserStarCount{" +
                "id=" + id +
                ", user=" + user +
                ", goldStarCount=" + goldStarCount +
                ", silverStarCount=" + silverStarCount +
                ", bronzeStarCount=" + bronzeStarCount +
                '}';
    }
}
