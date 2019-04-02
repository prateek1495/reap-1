package com.prateek.reap.Service;


import com.prateek.reap.Entity.ResponseDto;
import com.prateek.reap.Entity.Star;
import com.prateek.reap.Entity.User;
import com.prateek.reap.Entity.UserRole;
import com.prateek.reap.Repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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


    public boolean checkUserExists(String email, boolean active) {
        List<User> userList = signUpRepository.findByEmailAndActive(email, active);
        return userList.size() > 0;
    }


    public void save(User user) {
        UserRole userRole = userRoleService.checkByName("USER");
        user.getRoles().add(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        signUpRepository.save(user);
    }

    public Iterable<User> findAll() {
        return signUpRepository.findAll();
    }



   /* public ResponseDto getAllUser() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(true);
        responseDto.setData(signUpRepository.findAll());
        return responseDto;
    }*/


    /*public UserResponseDto findAllDetailOfUsers() {
        return null;
    }*/


    public void deleteUserRole(int roleId, int userId) {
        Optional<UserRole> userRole = userRoleService.findById(roleId);
        Optional<User> user = signUpRepository.findById(userId);
        user.get().getRoles().remove(userRole.get());
        signUpRepository.save(user.get());
        userStarCountService.setDefaultStarsAccordingToRole(user.get(), userRole.get(), "DELETE");
    }


    public Optional<User> findById(int userId) {
        return signUpRepository.findById(userId);
    }


    public void addUserRole(int roleId, int userId) {
        Optional<UserRole> role = userRoleService.findById(roleId);
        Optional<User> user = signUpRepository.findById(userId);
        userStarCountService.setDefaultStarsAccordingToRole(user.get(), role.get(), "ADD");
        user.get().getRoles().add(role.get());
        signUpRepository.save(user.get());
    }


  /*  public UserResponseDto constructUserResponseDto(User user, Iterable<UserRole> roles) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUser(user);
        userResponseDto.setAllRoles(roles);
        return userResponseDto;
    }
*/

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


    public String saveImagePath(MultipartFile profilePicture) throws IOException {
        String filePath="/home/prateek/IdeaProjects/reap/src/main/resources/static/assets/profileImages/";
        byte[] bytes =profilePicture.getBytes();
        Path path = Paths.get(filePath+profilePicture.getOriginalFilename());
        Files.write(path,bytes);
        return "/assets/profileImages"+profilePicture.getOriginalFilename();
    }

}
