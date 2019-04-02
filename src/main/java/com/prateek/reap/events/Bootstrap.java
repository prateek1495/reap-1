package com.prateek.reap.events;


import com.prateek.reap.Entity.User;
import com.prateek.reap.Entity.UserRole;
import com.prateek.reap.Repository.SignUpRepository;
import com.prateek.reap.Repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Iterator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Component
public class Bootstrap {

    @Autowired
    SignUpRepository signUpRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

  @Autowired
    BCryptPasswordEncoder passwordEncoder;



    @EventListener(ContextRefreshedEvent.class)
    public void seedData() {
        userData();
    }

    private void userData() {

        User adminUser = new User();
        UserRole admin = new UserRole();
        UserRole user = new UserRole();
        UserRole supervisor = new UserRole();
        UserRole practiceHead = new UserRole();

        Iterator<User> userIterator = signUpRepository.findAll().iterator();
        if (!userIterator.hasNext()) {

            adminUser.setFirstName("Admin");
            adminUser.setLastName("Admin");
            adminUser.setEmail("mailforprateek@gmail.com");
            adminUser.setPassword(passwordEncoder.encode("12345"));


            adminUser.setActive(true);
            adminUser.setPoints(0);

        }

        Iterator<UserRole> userRoleIterator = userRoleRepository.findAll().iterator();
        if (!userRoleIterator.hasNext()) {

            admin.setId(1);
            admin.setName("ADMIN");
            admin.setPriority(0);
            admin.setGoldStar(3);
            admin.setSilverStar(2);
            admin.setBronzeStar(1);

            user.setId(2);
            user.setPriority(1);
            user.setName("USER");
            user.setGoldStar(3);
            user.setSilverStar(2);
            user.setBronzeStar(1);

            supervisor.setId(3);
            supervisor.setPriority(2);
            supervisor.setName("SUPERVISOR");
            supervisor.setGoldStar(6);
            supervisor.setSilverStar(4);
            supervisor.setBronzeStar(2);


            practiceHead.setId(4);
            practiceHead.setPriority(3);
            practiceHead.setName("PRACTICEHEAD");
            practiceHead.setGoldStar(9);
            practiceHead.setSilverStar(6);
            practiceHead.setBronzeStar(3);


        }

        adminUser.getRoles().add(admin);

        signUpRepository.save(adminUser);
        userRoleRepository.save(admin);
        userRoleRepository.save(user);
        userRoleRepository.save(supervisor);
        userRoleRepository.save(practiceHead);


    }


}

