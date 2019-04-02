package com.prateek.reap.Service;

import com.prateek.reap.Entity.Star;
import com.prateek.reap.Entity.User;
import com.prateek.reap.Entity.UserStarReceived;
import com.prateek.reap.Repository.UserStarReceivedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStarReceivedService {

    @Autowired
    private  UserStarReceivedRepository userStarReceivedRepository;

    public Iterable<UserStarReceived> findAll() {
        return userStarReceivedRepository.findAll();
    }

    public void save(UserStarReceived starReceived) {
        userStarReceivedRepository.save(starReceived);
    }


    public UserStarReceived findUserStarCount(User receiverUser) {
        return userStarReceivedRepository.findByUser(receiverUser);
    }

    public void decrementReceiverStarAfterRevocation(User receiver, Star star) {

        UserStarReceived starCount = findUserStarCount(receiver);

        switch (star.getName().toUpperCase()) {

            case "GOLD":
                starCount.setGoldStarRecieved(starCount.getGoldStarRecieved() - 1);
                userStarReceivedRepository.save(starCount);
                break;

            case "BRONZE":
                starCount.setBronzeStarRecieved(starCount.getBronzeStarRecieved() - 1);
                userStarReceivedRepository.save(starCount);

                break;

            case "SILVER":
                starCount.setSilverStarRecieved(starCount.getSilverStarRecieved() - 1);
                userStarReceivedRepository.save(starCount);
                break;


        }

    }


    public void incrementUserStar(User receiverUser, Star star) {
        UserStarReceived starCount = findUserStarCount(receiverUser);

        switch (star.getName().toUpperCase()) {

            case "GOLD":
                starCount.setGoldStarRecieved(starCount.getGoldStarRecieved() + 1);
                userStarReceivedRepository.save(starCount);
                break;

            case "SILVER":
                starCount.setSilverStarRecieved(starCount.getSilverStarRecieved() + 1);
                userStarReceivedRepository.save(starCount);
                break;

            case "BRONZE":
                starCount.setBronzeStarRecieved(starCount.getBronzeStarRecieved() + 1);
                userStarReceivedRepository.save(starCount);

                break;

        }
    }

}

