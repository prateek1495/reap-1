package com.prateek.reap.Service;


import com.prateek.reap.Entity.*;
import com.prateek.reap.Repository.SignUpRepository;
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

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class SignUpService {

    @Autowired
    SignUpRepository signUpRepository;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private  UserStarCountService userStarCountService;

    @Autowired
    UserStarReceivedService userStarReceivedService;



    public void saveUser(User responseData) {
        // responseData.setUserPassword(new BCryptPasswordEncoder().encode(responseData.getUserPassword()));
        signUpRepository.save(responseData);
    }

   /* public List<User> checkUser(User responseData) {

        return signUpRepository.findByEmail(responseData.getEmail());

    }*/

    public List<User> checkEmailAndActive(String email, boolean active) {
        return signUpRepository.findByEmailAndActive(email, active);
    }

    public User checkByEmail(String email) {
        return signUpRepository.findByEmail(email);
    }

    public User findUserByResetToken(String resetToken) {
        return signUpRepository.findByToken(resetToken);
    }

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


    public boolean checkUserExists(String email, boolean active) {
        List<User> userList = signUpRepository.findByEmailAndActive(email, active);
        return userList.size() > 0;
    }


    public void save(User user,MultipartFile file) throws IOException {
        UserRole userRole = userRoleService.checkByName("USER");

        user.getRoles().add(userRole);

        String imagePath = saveImagePath(file);
        user.setImageUrl(imagePath);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);

        /*UserRole userRole1=new UserRole();
        userRole1.setId(1);
        userRole1.setName("USER");
        userRole1.setGoldStar(new Integer(3));
        userRole1.setSilverStar(new Integer(2));
        userRole1.setBronzeStar(new Integer(1));
        userRole1.setPriority(3);


        UserRole userRole2=new UserRole();
        userRole1.setId(2);
        userRole1.setName("ADMIN");
        userRole1.setGoldStar(new Integer(3));
        userRole1.setSilverStar(new Integer(2));
        userRole1.setBronzeStar(new Integer(1));
        userRole1.setPriority(4);

        UserRole userRole3=new UserRole();
        userRole1.setId(1);
        userRole1.setName("SUPERVISOR");
        userRole1.setGoldStar(new Integer(6));
        userRole1.setSilverStar(new Integer(3));
        userRole1.setBronzeStar(new Integer(2));
        userRole1.setPriority(2);

        UserRole userRole4=new UserRole();
        userRole1.setId(1);
        userRole1.setName("PRACTICEHEAD");
        userRole1.setGoldStar(new Integer(9));
        userRole1.setSilverStar(new Integer(6));
        userRole1.setBronzeStar(new Integer(3));
        userRole1.setPriority(1);
        userRoleService.save(userRole1);
        userRoleService.save(userRole2);
        userRoleService.save(userRole3);
        userRoleService.save(userRole4);
*/
      User newUser= signUpRepository.save(user);

        System.out.println(newUser + "+++++++++++");

        UserStarCount userStarCount=new UserStarCount();

        userStarCount.setUser(newUser);
        userStarCount.setGoldStarCount(userRole.getGoldStar());
        userStarCount.setSilverStarCount(userRole.getSilverStar());
        userStarCount.setBronzeStarCount(userRole.getBronzeStar());
        userStarCountService.save(userStarCount);
        UserStarReceived userStarReceived=new UserStarReceived();
        userStarReceived.setUser(newUser);
        userStarReceived.setGoldStarRecieved(0);
        userStarReceived.setSilverStarRecieved(0);
        userStarReceived.setBronzeStarRecieved(0);
        userStarReceivedService.save(userStarReceived);
        //signUpRepository.save(user);
    }



    public String saveImagePath(MultipartFile profilePicture) throws IOException {
        String filePath="/home/prateek/IdeaProjects/reap/out/production/resources/static/assets/profileImages/";
        byte[] bytes =profilePicture.getBytes();
        Path path = Paths.get(filePath+profilePicture.getOriginalFilename());
        Files.write(path,bytes);
        return "/assets/profileImages/"+profilePicture.getOriginalFilename();
    }

    public Iterable<User> findAll() {
        return signUpRepository.findAll();
    }



    public List<User>getAllUser()
    {
     List<User> users= (List<User>) signUpRepository.findAll();
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

        if(user.getRoles().contains(role))
        {
            return false;
        }
        getPriority(user,role);
        user.getRoles().add(role);
        signUpRepository.save(user);
      return true;

    }

public void getPriority(User user,UserRole role) {
    int p1 = role.getPriority();
    int currentPriority = user.getRoles().stream().max(Comparator.comparing(UserRole::getPriority)).get().getPriority();
    if (p1 > currentPriority) {
      UserStarCount userStarCount=  userStarCountService.findUserStarCount(user);
      userStarCount.setGoldStarCount(role.getGoldStar());
      userStarCount.setSilverStarCount(role.getSilverStar());
      userStarCount.setBronzeStarCount(role.getBronzeStar());
      userStarCountService.save(userStarCount);

    }
    else {

    }

}

    public void deactivateUser(int id) {
        Optional<User> user = signUpRepository.findById(id);
        user.get().setActive(false);
        signUpRepository.save(user.get());
    }


    public void activateUser(int id) {
        Optional<User> user = signUpRepository.findById(id);
        user.get().setActive(true);
        signUpRepository.save(user.get());
    }




}
