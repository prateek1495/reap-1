package com.prateek.reap.service;

import com.prateek.reap.entity.Star;
import com.prateek.reap.entity.User;
import com.prateek.reap.entity.UserStarReceived;
import com.prateek.reap.repository.UserStarReceivedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStarReceivedService {

    @Autowired
    private  UserStarReceivedRepository userStarReceivedRepository;

    @Autowired
    private SignUpService signUpService;

    public Iterable<UserStarReceived> findAll() {
        return userStarReceivedRepository.findAll();
    }

    public void save(UserStarReceived starReceived) {
        userStarReceivedRepository.save(starReceived);
    }

    public UserStarReceived findByUserId(Integer id) {

        return userStarReceivedRepository.findByUser_Id(id);
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

    public List<UserStarReceived> findByTopNewers() {
        return userStarReceivedRepository.findByTopNewers();

    }
}

