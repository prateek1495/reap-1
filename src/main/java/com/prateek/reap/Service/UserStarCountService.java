package com.prateek.reap.Service;


import com.prateek.reap.Entity.*;
import com.prateek.reap.Repository.UserStarCountRepository;
import com.prateek.reap.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserStarCountService {

    @Autowired
    private  UserStarCountRepository userStarCountRepository;

    public Iterable<UserStarCount> findAll() {
        return userStarCountRepository.findAll();
    }

    public void save(UserStarCount starCount) {
        userStarCountRepository.save(starCount);
    }

    public UserStarCount findByUserId(Integer id) {

        return userStarCountRepository.findByUser_Id(id);
    }
    public UserStarCount findUserStarCount(User senderUser) {
        return userStarCountRepository.findByUser(senderUser);
    }




    public void incrementGiverStarAfterRevocation(User giver, Star star) {
        UserStarCount starCount = findUserStarCount(giver);

        switch (star.getName().toUpperCase()) {

            case "GOLD":
                starCount.setGoldStarCount(starCount.getGoldStarCount() + 1);
                userStarCountRepository.save(starCount);
                break;

            case "SILVER":
                starCount.setSilverStarCount(starCount.getSilverStarCount() + 1);
                userStarCountRepository.save(starCount);
                break;

            case "BRONZE":
                starCount.setBronzeStarCount(starCount.getBronzeStarCount() + 1);
                userStarCountRepository.save(starCount);
                break;

        }

    }


    public boolean decrementUserStar(User senderUser, Star star) {
        UserStarCount starCount = findUserStarCount(senderUser);

        switch (star.getName().toUpperCase()) {

            case "GOLD":

                if (starCount.getGoldStarCount() >= 1) {
                    starCount.setGoldStarCount(starCount.getGoldStarCount() - 1);
                    userStarCountRepository.save(starCount);
                } else
                    return false;

                break;

            case "SILVER":

                if (starCount.getSilverStarCount() >= 1) {
                    starCount.setSilverStarCount(starCount.getSilverStarCount() - 1);
                    userStarCountRepository.save(starCount);
                } else
                    return false;
                break;

            case "BRONZE":

                if (starCount.getBronzeStarCount() >= 1) {
                    starCount.setBronzeStarCount(starCount.getBronzeStarCount() - 1);
                    userStarCountRepository.save(starCount);
                } else
                    return false;
                break;
            default:
                return false;
        }

        return true;

    }
}

