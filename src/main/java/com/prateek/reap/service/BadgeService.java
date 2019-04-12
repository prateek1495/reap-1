package com.prateek.reap.service;


import com.prateek.reap.entity.BadgesGiven;
import com.prateek.reap.entity.Star;
import com.prateek.reap.entity.User;
import com.prateek.reap.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.prateek.reap.util.Constants.*;


@Service
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private StarService starService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserStarCountService userStarCountService;

    @Autowired
    private UserStarReceivedService userStarReceivedService;

    public void save(BadgesGiven badgesGiven) {
        badgeRepository.save(badgesGiven);
    }

    public boolean saveRecognitionData(String starType, String receiverEmail, String senderEmail, String comment) {

        User receiverUser = signUpService.checkByEmail(receiverEmail);
        User senderUser = signUpService.checkByEmail(senderEmail);
        Star star = starService.findByName(starType);
        boolean LimitCheck = userStarCountService.decrementUserStar(senderUser, star);
        if (!LimitCheck) {
            return false;
        }
        userStarReceivedService.incrementUserStar(receiverUser, star);
        signUpService.changeUserPoints(receiverUser, star, DECIDER_ADD);


        BadgesGiven badgesGiven = sendRecognition(receiverUser, senderUser, star, comment);
        badgeRepository.save(badgesGiven);

        emailService.sendEmailToSingleRecipient(senderUser.getEmail(), RECOGNITION_NEWER,
                GAVE_STAR + " " + star.getName().toUpperCase() + TO + receiverUser
                        .getEmail() + " " + REASON_REVOKATION
                        + " \n\n" + comment);


        emailService.sendEmailToSingleRecipient(receiverUser.getEmail(), RECOGNITION_NEWER,
                CONGRATULATION+ "\n" + receiverUser.getFirstName() + " " + receiverUser.getLastName() + YOU +
                        HAVE_RECEIVED + star.getName().toUpperCase() + STAR_FROM + senderUser
                        .getFirstName() + " " + senderUser.getLastName()
                        + " " + REASON_REVOKATION + "\n\n" + comment);


        return true;

    }


    private BadgesGiven sendRecognition(User receiverUser, User senderUser, Star star, String comment) {
        BadgesGiven badgesGiven = new BadgesGiven();
        badgesGiven.setFlag(true);
        badgesGiven.setGiver(senderUser);
        badgesGiven.setReceiver(receiverUser);
        badgesGiven.setComment(comment);
        badgesGiven.setStar(star);
        return badgesGiven;
    }

    public List<BadgesGiven> findAllSharedRecognition(Integer id) {
        Optional<User> user = signUpService.findById(id);
        return badgeRepository.findByGiverAndFlag(new Sort(Sort.Direction.DESC, UPDATED_AT), user.get(), true);
    }

    public List<BadgesGiven> findAllReceivedRecognition(Integer id) {
        Optional<User> user = signUpService.findById(id);
        return badgeRepository.findByReceiverAndFlag(new Sort(Sort.Direction.DESC, UPDATED_AT), user.get(), true);
    }


    public List<BadgesGiven> findAllRecognitionByUser(Integer id) {
        Optional<User> user = signUpService.findById(id);
        return badgeRepository
                .findAllByGiverOrReceiver(new Sort(Sort.Direction.DESC, UPDATED_AT), user.get(), user.get());
    }


    public List<BadgesGiven> findAllByDate() {
        return badgeRepository.findAllByFlag(new Sort(Sort.Direction.DESC, UPDATED_AT), true);
    }

    public List<BadgesGiven> findAllByDateAndNameLike(String name) {
        return badgeRepository.findByReceiverFirstNameLike("%" + name.substring(0, name.length() - 2) + "%");
    }

    public void recognitionDelete(Integer id, String starName, String comment) {

        Optional<BadgesGiven> badgesGiven = badgeRepository.findById(id);
        Star star = starService.findByName(starName);

        if (badgesGiven.isPresent()) {

            User giver = badgesGiven.get().getGiver();
            User receiver = badgesGiven.get().getReceiver();
            BadgesGiven badgesGiven1 = badgesGiven.get();
            badgesGiven1.setFlag(false);

            userStarCountService.incrementGiverStarAfterRevocation(giver, star);

            userStarReceivedService.decrementReceiverStarAfterRevocation(receiver, star);

            signUpService.changeUserPoints(receiver, star, DECIDER_DELETE );

            sendRevocationEmails(giver, receiver, comment);
        }
    }

    public void sendRevocationEmails(User giver, User receiver, String comment) {

        emailService.sendEmailToSingleRecipient(
                giver.getEmail(),
                MAIL_SUBJECT_RECOGNITION_REVOKED,
                RECOGNITION_YOU_GAVE_TO
                        + " "
                        + receiver.getFirstName()
                        + " "
                        + REVOKED_BY_ADMIN
                        + " "
                        + REASON_REVOKATION
                        + " \n"
                        + comment
                        + "\n");

        emailService.sendEmailToSingleRecipient(
                receiver.getEmail(),
                MAIL_SUBJECT_RECOGNITION_REVOKED,
                RECOGNITION_YOU_RECEIVED
                        + " "
                        + giver.getFirstName()
                        + " "
                        + REVOKED_BY_ADMIN
                        + " "
                        + REASON_REVOKATION
                        + " \n"
                        + comment
                        + "\n"
                        + CONTACT_ADMIN );
    }


    public List<BadgesGiven> findAllBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return badgeRepository
                .findAllByUpdatedAtBetween(new Sort(Sort.Direction.DESC, UPDATED_AT), startDate, endDate);
    }

}



