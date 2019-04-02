package com.prateek.reap.Service;


import com.prateek.reap.Entity.BadgesGiven;
import com.prateek.reap.Entity.Star;
import com.prateek.reap.Entity.User;
import com.prateek.reap.Repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.prateek.reap.util.Constants.*;


@Service
public class BadgeService {

    @Autowired
    private  BadgeRepository badgeRepository;

    @Autowired
    private  SignUpService signUpService;

    private  StarService starService;

    @Autowired
    private  EmailService emailService;

    private  UserStarCountService userStarCountService;

    private  UserStarReceivedService userStarReceivedService;



    public Iterable<BadgesGiven> findAll() {
        return badgeRepository.findAll();
    }


    public void save(BadgesGiven badgesGiven) {
        badgeRepository.save(badgesGiven);
    }



    public List<BadgesGiven> findAllActiveRecognition(boolean flag) {

        return badgeRepository.findByFlag(flag);
    }


    public void recognitionDelete(Integer id, String starName, String comment) {

        Optional<BadgesGiven> badgesGiven = badgeRepository.findById(id);
        Star star = starService.getStarByName(starName);

        if (badgesGiven.isPresent()) {

            User giver = badgesGiven.get().getGiver();
            User receiver = badgesGiven.get().getReceiver();

            badgeRepository.delete(badgesGiven.get());

            userStarCountService.incrementGiverStarAfterRevocation(giver, star);

            userStarReceivedService.decrementReceiverStarAfterRevocation(receiver, star);

            signUpService.changeUserPoints(receiver, star, "DELETE");

            sendRevocationEmails(giver, receiver, comment);
        }


    }

    private void sendRevocationEmails(User giver, User receiver, String comment) {

        emailService.sendEmailToSingleRecipient(giver.getEmail(), MAIL_SUBJECT_RECOGNITION_REVOKED,
                RECOGNITION_YOU_GAVE_TO + " " + receiver.getFirstName() + " " + REVOKED_BY_ADMIN +" "+
                        REASON_REVOKATION + " \n" + comment
                        + "\n");

        emailService.sendEmailToSingleRecipient(receiver.getEmail(), MAIL_SUBJECT_RECOGNITION_REVOKED
                , RECOGNITION_YOU_RECEIVED + " " + giver.getFirstName() + " " + REVOKED_BY_ADMIN + " " +
                        REASON_REVOKATION + " \n" + comment
                        + "\n" + CONTACT_ADMIN);

    }



    public boolean saveRecognitionData(String starType, String receiverEmail, String senderEmail, String comment) {

        User receiverUser = signUpService.checkByEmail(receiverEmail);
        User senderUser = signUpService.checkByEmail(senderEmail);
        Star star = starService.getStarByName(starType);

        userStarReceivedService.incrementUserStar(receiverUser, star);
        signUpService.changeUserPoints(receiverUser, star, "ADD");
        boolean senderStarLimitCheck = userStarCountService.decrementUserStar(senderUser, star);
        if (!senderStarLimitCheck)
            return false;

        BadgesGiven badgesGiven = constructRecognition(receiverUser, senderUser, star, comment);
        badgeRepository.save(badgesGiven);

        constructEmail(receiverUser, senderUser, star, comment);


        return true;

    }

    private void constructEmail(User receiverUser, User senderUser, Star star, String comment) {

        emailService.sendEmailToSingleRecipient(senderUser.getEmail(), RECOGNITION_NEWER,
                GAVE_STAR + " " + star.getName().toUpperCase() + " to " + receiverUser.getEmail() + " " + REASON_REVOKATION
                        + " \n\n" + comment);


        emailService.sendEmailToSingleRecipient(receiverUser.getEmail(), RECOGNITION_NEWER,
                CONGRATULATION + "\n" + receiverUser.getFirstName() + " " + receiverUser.getLastName() + " you " +
                        HAVE_RECEIVED + star.getName().toUpperCase() + " star from " + senderUser.getFirstName() + " " + senderUser.getLastName()
                        + " " + REASON_REVOKATION + "\n\n" + comment);


    }


    private BadgesGiven constructRecognition(User receiverUser, User senderUser, Star star, String comment) {
        BadgesGiven badgesGiven = new BadgesGiven();
        badgesGiven.setFlag(true);
        badgesGiven.setGiver(senderUser);
        badgesGiven.setReceiver(receiverUser);
        badgesGiven.setComment(comment);
        badgesGiven.setStar(star);
        return badgesGiven;
    }


}

