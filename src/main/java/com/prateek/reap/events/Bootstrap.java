/*
package com.prateek.reap.events;


import com.prateek.reap.entity.*;
import com.prateek.reap.repository.SignUpRepository;
import com.prateek.reap.repository.UserRoleRepository;
import com.prateek.reap.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Iterator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Component
public class Bootstrap {


    @Autowired
    private SignUpService signUpService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private StarService starService;

    @Autowired
    private BadgeService badgeService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserStarCountService userStarCountService;

    @Autowired
    private UserStarReceivedService userStarReceivedService;

    @Autowired
    private ItemService itemService;


    @EventListener(ContextRefreshedEvent.class)
    public void seedData() {
        userData();
    }

    private void userData() {
        User adminUser = new User();
        User vagish = new User();
        User vd = new User();
        User gagan = new User();
        User prateek = new User();
        User arpit = new User();
        User lohit = new User();
        User rishabh = new User();

        UserStarCount starCount1 = new UserStarCount();
        UserStarCount starCount2 = new UserStarCount();
        UserStarCount starCount3 = new UserStarCount();
        UserStarCount starCount4 = new UserStarCount();
        UserStarCount starCount5 = new UserStarCount();
        UserStarCount starCount6 = new UserStarCount();
        UserStarCount starCount7 = new UserStarCount();
        UserStarCount starCount8 = new UserStarCount();

        UserRole admin = new UserRole();
        UserRole user = new UserRole();
        UserRole supervisor = new UserRole();
        UserRole practiceHead = new UserRole();

        Star goldStar = new Star();
        Star silverStar = new Star();
        Star bronzeStar = new Star();

        BadgesGiven badgesGiven = new BadgesGiven();
        BadgesGiven badgesGiven1 = new BadgesGiven();
        BadgesGiven badgesGiven2 = new BadgesGiven();
        BadgesGiven badgesGiven3 = new BadgesGiven();
        BadgesGiven badgesGiven4 = new BadgesGiven();
        BadgesGiven badgesGiven5 = new BadgesGiven();
        BadgesGiven badgesGiven6 = new BadgesGiven();
        BadgesGiven badgesGiven7 = new BadgesGiven();
        BadgesGiven badgesGiven8 = new BadgesGiven();
        BadgesGiven badgesGiven9 = new BadgesGiven();
        BadgesGiven badgesGiven10 = new BadgesGiven();

        UserStarReceived starReceived1 = new UserStarReceived();
        UserStarReceived starReceived2 = new UserStarReceived();
        UserStarReceived starReceived3 = new UserStarReceived();
        UserStarReceived starReceived4 = new UserStarReceived();
        UserStarReceived starReceived5 = new UserStarReceived();
        UserStarReceived starReceived6 = new UserStarReceived();
        UserStarReceived starReceived7 = new UserStarReceived();
        UserStarReceived starReceived8 = new UserStarReceived();


        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();
        Item item4 = new Item();
        Item item5 = new Item();
        Item item6 = new Item();
        Item item7 = new Item();
        Item item8 = new Item();
        Item item9 = new Item();
        Item item10 = new Item();

  Iterator<BadgesGiven> badgesGivenIterator = badgeService.findAll().iterator();
        if (!badgesGivenIterator.hasNext()) {

            badgesGiven.setGiver(vagish);
            badgesGiven.setReceiver(gagan);
            badgesGiven.setComment("Comment 1");
            badgesGiven.setStar(goldStar);
            badgesGiven.setFlag(true);

            badgesGiven1.setGiver(vagish);
            badgesGiven1.setReceiver(prateek);
            badgesGiven1.setComment("Comment 2");
            badgesGiven1.setStar(bronzeStar);
            badgesGiven1.setFlag(true);

            badgesGiven2.setGiver(adminUser);
            badgesGiven2.setReceiver(arpit);
            badgesGiven2.setComment("Comment 3");
            badgesGiven2.setStar(silverStar);
            badgesGiven2.setFlag(true);

            badgesGiven3.setGiver(adminUser);
            badgesGiven3.setReceiver(lohit);
            badgesGiven3.setComment("Comment 4");
            badgesGiven3.setStar(goldStar);
            badgesGiven3.setFlag(true);

            badgesGiven4.setGiver(vagish);
            badgesGiven4.setReceiver(rishabh);
            badgesGiven4.setComment("Comment 5");
            badgesGiven4.setStar(bronzeStar);
            badgesGiven4.setFlag(true);

            badgesGiven5.setGiver(adminUser);
            badgesGiven5.setReceiver(lohit);
            badgesGiven5.setComment("Comment 6");
            badgesGiven5.setStar(silverStar);
            badgesGiven5.setFlag(true);

            badgesGiven6.setGiver(vagish);
            badgesGiven6.setReceiver(adminUser);
            badgesGiven6.setComment("Comment 7");
            badgesGiven6.setStar(goldStar);
            badgesGiven6.setFlag(true);

            badgesGiven7.setGiver(prateek);
            badgesGiven7.setReceiver(vagish);
            badgesGiven7.setComment("Comment 8");
            badgesGiven7.setStar(bronzeStar);
            badgesGiven7.setFlag(true);

            badgesGiven8.setGiver(gagan);
            badgesGiven8.setReceiver(adminUser);
            badgesGiven8.setComment("Comment 9");
            badgesGiven8.setStar(silverStar);
            badgesGiven8.setFlag(true);

            badgesGiven9.setGiver(rishabh);
            badgesGiven9.setReceiver(vagish);
            badgesGiven9.setComment("Comment 10");
            badgesGiven9.setStar(goldStar);
            badgesGiven9.setFlag(true);

            badgesGiven10.setGiver(arpit);
            badgesGiven10.setReceiver(adminUser);
            badgesGiven10.setComment("Comment 11");
            badgesGiven10.setStar(goldStar);
            badgesGiven10.setFlag(true);
        }


        Iterator<Item> itemIterator = itemService.findAll().iterator();
        if (!itemIterator.hasNext()) {

            item1.setActive(true);
            item1.setPoints(350);
            item1.setName("Visting Card Holder");
            item1.setSize(false);
            item1.setImageUrl("/assets/images/laptop.png");

            item2.setActive(true);
            item2.setPoints(70);
            item2.setName("Paper Clip");
            item2.setSize(false);
            item2.setImageUrl("/assets/images/laptop.png");

            item3.setActive(true);
            item3.setPoints(750);
            item3.setName("Steel Sipper Bottle");
            item3.setSize(false);
            item3.setImageUrl("/assets/images/laptop.png");

            item4.setActive(true);
            item4.setPoints(130);
            item4.setName("Simley Desktop Message Card");
            item4.setSize(false);
            item4.setImageUrl("/assets/images/laptop.png");

            item5.setActive(true);
            item5.setPoints(140);
            item5.setName("Booklight Paper Clip");
            item5.setSize(false);
            item5.setImageUrl("/assets/images/laptop.png");

            item6.setActive(true);
            item6.setPoints(400);
            item6.setName("Laptop Sleeve");
            item6.setSize(false);
            item6.setImageUrl("/assets/images/laptop.png");

            item7.setActive(true);
            item7.setPoints(40);
            item7.setName("Pen");
            item7.setSize(false);
            item7.setImageUrl("/assets/images/laptop.png");

            item8.setActive(true);
            item8.setPoints(300);
            item8.setName("Mug");
            item8.setSize(false);
            item8.setImageUrl("/assets/images/laptop.png");

            item9.setActive(true);
            item9.setPoints(90);
            item9.setName("Mobile Stand");
            item9.setSize(false);
            item9.setImageUrl("/assets/images/laptop.png");

            item10.setActive(true);
            item10.setPoints(690);
            item10.setName("Passport Holder");
            item10.setSize(false);
            item10.setImageUrl("/assets/images/laptop.png");
        }

 Iterator<User> userIterator = signUpService.findAll().iterator();
        if (!userIterator.hasNext()) {

            adminUser.setFirstName("Admin");
            adminUser.setLastName("Admin");
            adminUser.setEmail("dixit.vagish@gmail.com");
            adminUser.setPassword(passwordEncoder.encode("123456"));
            adminUser.setActive(true);
            adminUser.setPoints(0);
            adminUser.setImageUrl("assets/user.png");

            vagish.setFirstName("Vagish");
            vagish.setLastName("Dixit");
            vagish.setEmail("vagish.dixit@bvicam.in");
            vagish.setPassword(passwordEncoder.encode("1234"));
            vagish.setActive(true);
            vagish.setPoints(0);
            vagish.setImageUrl("assets/user.png");

            vd.setFirstName("VagishDixit");
            vd.setLastName("DixitVagish");
            vd.setEmail("vagish.dixit@tothenew.com");
            vd.setPassword(passwordEncoder.encode("1234"));
            vd.setActive(true);
            vd.setPoints(0);
            vd.setImageUrl("assets/user.png");

            prateek.setFirstName("Prateek");
            prateek.setLastName("Nagar");
            prateek.setEmail("prateek.nagar@tothenew.com");
            prateek.setPassword(passwordEncoder.encode("1234"));
            prateek.setActive(true);
            prateek.setPoints(0);
            prateek.setImageUrl("assets/user.png");

            gagan.setFirstName("Gagan");
            gagan.setLastName("Kushwaha");
            gagan.setEmail("gagan.kushwaha@tothenew.com");
            gagan.setPassword(passwordEncoder.encode("1234"));
            gagan.setActive(true);
            gagan.setPoints(0);
            gagan.setImageUrl("assets/user.png");

            arpit.setFirstName("Arpit");
            arpit.setLastName("Gupta");
            arpit.setEmail("arpit.gupta@tothenew.com");
            arpit.setPassword(passwordEncoder.encode("1234"));
            arpit.setActive(true);
            arpit.setPoints(0);
            arpit.setImageUrl("assets/user.png");

            lohit.setFirstName("Lohit");
            lohit.setLastName("Ahooja");
            lohit.setEmail("lohit.ahooja@tothenew.com");
            lohit.setPassword(passwordEncoder.encode("1234"));
            lohit.setActive(true);
            lohit.setPoints(0);
            lohit.setImageUrl("assets/user.png");

            rishabh.setFirstName("Rishabh");
            rishabh.setLastName("Rajput");
            rishabh.setEmail("rishabh.rajput@tothenew.com");
            rishabh.setPassword(passwordEncoder.encode("1234"));
            rishabh.setActive(true);
            rishabh.setPoints(0);
            rishabh.setImageUrl("assets/user.png");
        }

        Iterator<UserRole> userRoleIterator = userRoleService.findAll().iterator();
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

        Iterator<Star> starIterator = starService.findAll().iterator();
        if (!starIterator.hasNext()) {

            goldStar.setName("GOLD");
            goldStar.setWeight(30);

            silverStar.setName("SILVER");
            silverStar.setWeight(20);

            bronzeStar.setName("BRONZE");
            bronzeStar.setWeight(10);
        }

        Iterator<UserStarCount> starCountIterator = userStarCountService.findAll().iterator();
        if (!starCountIterator.hasNext()) {

            starCount1.setUser(adminUser);
            starCount1.setBronzeStarCount(admin.getBronzeStar());
            starCount1.setSilverStarCount(admin.getSilverStar());
            starCount1.setGoldStarCount(admin.getGoldStar());

            starCount2.setUser(vagish);
            starCount2.setSilverStarCount(supervisor.getSilverStar());
            starCount2.setGoldStarCount(supervisor.getGoldStar());
            starCount2.setBronzeStarCount(supervisor.getBronzeStar());

            starCount3.setUser(vd);
            starCount3.setGoldStarCount(practiceHead.getGoldStar());
            starCount3.setBronzeStarCount(practiceHead.getBronzeStar());
            starCount3.setSilverStarCount(practiceHead.getSilverStar());

            starCount4.setUser(gagan);
            starCount4.setSilverStarCount(practiceHead.getSilverStar());
            starCount4.setBronzeStarCount(practiceHead.getBronzeStar());
            starCount4.setGoldStarCount(practiceHead.getGoldStar());

            starCount5.setUser(prateek);
            starCount5.setBronzeStarCount(supervisor.getBronzeStar());
            starCount5.setGoldStarCount(supervisor.getGoldStar());
            starCount5.setSilverStarCount(supervisor.getSilverStar());

            starCount6.setUser(arpit);
            starCount6.setGoldStarCount(practiceHead.getGoldStar());
            starCount6.setSilverStarCount(practiceHead.getSilverStar());
            starCount6.setBronzeStarCount(practiceHead.getBronzeStar());

            starCount7.setUser(lohit);
            starCount7.setGoldStarCount(supervisor.getGoldStar());
            starCount7.setSilverStarCount(supervisor.getSilverStar());
            starCount7.setBronzeStarCount(supervisor.getBronzeStar());

            starCount8.setUser(rishabh);
            starCount8.setGoldStarCount(practiceHead.getGoldStar());
            starCount8.setSilverStarCount(practiceHead.getSilverStar());
            starCount8.setBronzeStarCount(practiceHead.getBronzeStar());
        }

        Iterator<UserStarReceived> starReceivedIterator = userStarReceivedService.findAll().iterator();
        if (!starReceivedIterator.hasNext()) {

            starReceived1.setBronzeStarRecieved(0);
            starReceived1.setUser(adminUser);
            starReceived1.setSilverStarRecieved(0);
            starReceived1.setGoldStarRecieved(0);

            starReceived2.setUser(vagish);
            starReceived2.setBronzeStarRecieved(0);
            starReceived2.setGoldStarRecieved(0);
            starReceived2.setSilverStarRecieved(0);

            starReceived3.setUser(vd);
            starReceived3.setGoldStarRecieved(0);
            starReceived3.setSilverStarRecieved(0);
            starReceived3.setBronzeStarRecieved(0);

            starReceived4.setUser(gagan);
            starReceived4.setBronzeStarRecieved(0);
            starReceived4.setSilverStarRecieved(0);
            starReceived4.setGoldStarRecieved(0);

            starReceived5.setUser(prateek);
            starReceived5.setBronzeStarRecieved(0);
            starReceived5.setSilverStarRecieved(0);
            starReceived5.setGoldStarRecieved(0);

            starReceived6.setUser(arpit);
            starReceived6.setSilverStarRecieved(0);
            starReceived6.setBronzeStarRecieved(0);
            starReceived6.setGoldStarRecieved(0);

            starReceived7.setUser(lohit);
            starReceived7.setSilverStarRecieved(0);
            starReceived7.setGoldStarRecieved(0);
            starReceived7.setBronzeStarRecieved(0);

            starReceived8.setUser(rishabh);
            starReceived8.setGoldStarRecieved(0);
            starReceived8.setBronzeStarRecieved(0);
            starReceived8.setSilverStarRecieved(0);
        }


        adminUser.getRoles().add(admin);
        adminUser.getRoles().add(practiceHead);
        vagish.getRoles().add(supervisor);
        vd.getRoles().add(practiceHead);
        gagan.getRoles().add(practiceHead);
        prateek.getRoles().add(supervisor);
        arpit.getRoles().add(practiceHead);
        lohit.getRoles().add(supervisor);
        rishabh.getRoles().add(practiceHead);

        signUpService.saveUser(adminUser);
        signUpService.saveUser(vagish);
        signUpService.saveUser(vd);
        signUpService.saveUser(gagan);
        signUpService.saveUser(prateek);
        signUpService.saveUser(arpit);
        signUpService.saveUser(lohit);
        signUpService.saveUser(rishabh);

        userRoleService.save(admin);
        userRoleService.save(user);
        userRoleService.save(supervisor);
        userRoleService.save(practiceHead);

        starService.save(goldStar);
        starService.save(silverStar);
        starService.save(bronzeStar);

        badgeService.save(badgesGiven);
        badgeService.save(badgesGiven1);
        badgeService.save(badgesGiven2);
        badgeService.save(badgesGiven3);
        badgeService.save(badgesGiven4);
        badgeService.save(badgesGiven5);
        badgeService.save(badgesGiven6);
        badgeService.save(badgesGiven7);
        badgeService.save(badgesGiven8);
        badgeService.save(badgesGiven9);
        badgeService.save(badgesGiven10);

        userStarCountService.save(starCount1);
        userStarCountService.save(starCount2);
        userStarCountService.save(starCount3);
        userStarCountService.save(starCount4);
        userStarCountService.save(starCount5);
        userStarCountService.save(starCount6);
        userStarCountService.save(starCount7);
        userStarCountService.save(starCount8);

        userStarReceivedService.save(starReceived1);
        userStarReceivedService.save(starReceived2);
        userStarReceivedService.save(starReceived3);
        userStarReceivedService.save(starReceived4);
        userStarReceivedService.save(starReceived5);
        userStarReceivedService.save(starReceived6);
        userStarReceivedService.save(starReceived7);
        userStarReceivedService.save(starReceived8);


        itemService.save(item1);
        itemService.save(item2);
        itemService.save(item3);
        itemService.save(item4);
        itemService.save(item5);
        itemService.save(item6);
        itemService.save(item7);
        itemService.save(item8);
        itemService.save(item9);
        itemService.save(item10);

    }
}



*/
