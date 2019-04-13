package com.prateek.reap.service;


import com.prateek.reap.entity.*;
import com.prateek.reap.repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SignUpService {

    @Autowired
    private SignUpRepository signUpRepository;

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserStarReceivedService userStarReceivedService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserStarCountService userStarCountService;

    public void saveUser(User responseData) {
        signUpRepository.save(responseData);
    }

    public List<User> checkEmailAndActive(String email, boolean active) {
        return signUpRepository.findByEmailAndActive(email, active);
    }

    public User checkByEmail(String email) {
        return signUpRepository.findByEmail(email);
    }

/*
    public User findUserByResetToken(String resetToken) {
        return signUpRepository.findByToken(resetToken);
    }
*/

    public String saveTokenAndGenerateResetUrl(User user, String resetTokenUrl) {
        String token = generateAndSaveResetToken(user);
        return resetTokenUrl + token;
    }

    public User findByToken(String Token) {
        return signUpRepository.findByToken(Token);
    }

    private String generateAndSaveResetToken(User user) {
        String token = UUID.randomUUID().toString();
        user.setActive(false);
        user.setToken(token);
        signUpRepository.save(user);
        return token;
    }

    public void resetEmailTokenAndSetNewPassword(String token, String newPassword) {
        User user = signUpRepository.findByToken(token);
        user.setToken(null);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(newPassword));
        signUpRepository.save(user);
    }

    public Optional<User> findById(Integer userId) {
        return signUpRepository.findById(userId);
    }


   /* public boolean checkUserExists(String email, boolean active) {
        List<User> userList = signUpRepository.findByEmailAndActive(email, active);
        return userList.size() > 0;
    }*/


    public void save(User user, MultipartFile file) throws IOException {
        UserRole userRole = userRoleService.checkByName("USER");

        user.getRoles().add(userRole);

        String imagePath = saveImagePath(file);
        user.setImageUrl(imagePath);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);

        User newUser = signUpRepository.save(user);
        UserStarCount userStarCount = new UserStarCount();
        userStarCount.setUser(newUser);
        userStarCount.setGoldStarCount(userRole.getGoldStar());
        userStarCount.setSilverStarCount(userRole.getSilverStar());
        userStarCount.setBronzeStarCount(userRole.getBronzeStar());
        userStarCountService.save(userStarCount);
        UserStarReceived userStarReceived = new UserStarReceived();
        userStarReceived.setUser(newUser);
        userStarReceived.setGoldStarRecieved(0);
        userStarReceived.setSilverStarRecieved(0);
        userStarReceived.setBronzeStarRecieved(0);
        userStarReceivedService.save(userStarReceived);
    }


    public String saveImagePath(MultipartFile profilePicture) throws IOException {
        String filePath = "/home/prateek/IdeaProjects/reap/out/production/resources/static/assets/profileImages/";
        byte[] bytes = profilePicture.getBytes();
        Path path = Paths.get(filePath + profilePicture.getOriginalFilename());
        Files.write(path, bytes);
        return "/assets/profileImages/" + profilePicture.getOriginalFilename();
    }

    public Iterable<User> findAll() {
        return signUpRepository.findAll();
    }


    public List<User> getAllUser() {
        List<User> users = (List<User>) signUpRepository.findAll();
        return users;
    }

    public void changeUserPoints(User receiver, Star star, String decider) {

        int userPoints = receiver.getPoints();
        int receivedPoints = star.getWeight();

        switch (decider) {
            case "DELETE":
                userPoints -= receivedPoints;
                receiver.setPoints(userPoints);
                signUpRepository.save(receiver);
                break;
            case "ADD":
                userPoints += receivedPoints;
                receiver.setPoints(userPoints);
                signUpRepository.save(receiver);
                break;
        }

    }


    public Boolean allocateRole(UserRole role, User user) {
        if(user!=null) {
            if (user.getRoles().contains(role)) {
                return false;
            }
            getPriority(user, role);
            user.getRoles().add(role);
            signUpRepository.save(user);
            return true;
        }
        return false;
    }

    public Boolean deleteRole(UserRole role, User user) {
        if(user!=null) {
            if (user.getRoles().contains(role)) {
                user.getRoles().remove(role);
                int revokePriority = user.getRoles().stream().max(Comparator.comparing(UserRole::getPriority)).get()
                        .getPriority();
                UserRole role1 = userRoleService.findByPriority(revokePriority);
                UserStarCount userStarCount = userStarCountService.findUserStarCount(user);
                userStarCount.setGoldStarCount(role1.getGoldStar());
                userStarCount.setSilverStarCount(role1.getSilverStar());
                userStarCount.setBronzeStarCount(role1.getBronzeStar());
                userStarCountService.save(userStarCount);
                signUpRepository.save(user);
                return true;
            } else
                return false;
        }
        return false;

    }

    public Boolean changePointsByAdmin(User user1, Integer points) {
        if(user1!=null) {
            if (user1.isActive()) {
                user1.setPoints(points);
                signUpRepository.save(user1);
                return true;
            } else
                return false;
        }
        return false;

    }

    public void getPriority(User user, UserRole role) {
        if(user!=null) {
            int p1 = role.getPriority();
            int currentPriority = user.getRoles().stream().max(Comparator.comparing(UserRole::getPriority)).get()
                    .getPriority();
            if (p1 > currentPriority) {
                UserStarCount userStarCount = userStarCountService.findUserStarCount(user);
                userStarCount.setGoldStarCount(role.getGoldStar());
                userStarCount.setSilverStarCount(role.getSilverStar());
                userStarCount.setBronzeStarCount(role.getBronzeStar());
                userStarCountService.save(userStarCount);

            }
        }

    }

    public Boolean changeGoldStarByAdmin(User user1, Integer goldStar) {
        if(user1!=null) {
            if (user1.isActive()) {
                UserStarCount userStarCount=userStarCountService.findUserStarCount(user1);
                userStarCount.setGoldStarCount(goldStar);
                userStarCountService.save(userStarCount);
                signUpRepository.save(user1);
                return true;
            } else
                return false;
        }
        return false;
    }

    public Boolean changeSilverStarByAdmin(User user1, Integer silverStar) {
        if(user1!=null) {
            if (user1.isActive()) {
                UserStarCount userStarCount=userStarCountService.findUserStarCount(user1);
                userStarCount.setSilverStarCount(silverStar);
                userStarCountService.save(userStarCount);
                signUpRepository.save(user1);
                return true;
            } else
                return false;
        }
        return false;
    }


    public Boolean changeBronzeStarByAdmin(User user1, Integer bronzeStar) {
        if(user1!=null) {
            if (user1.isActive()) {
                UserStarCount userStarCount=userStarCountService.findUserStarCount(user1);
                userStarCount.setBronzeStarCount(bronzeStar);
                userStarCountService.save(userStarCount);
                signUpRepository.save(user1);
                return true;
            } else
                return false;
        }
        return false;
    }
}
