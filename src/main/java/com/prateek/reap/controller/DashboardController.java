package com.prateek.reap.controller;

import com.prateek.reap.entity.BadgesGiven;
import com.prateek.reap.entity.User;
import com.prateek.reap.entity.UserRole;
import com.prateek.reap.repository.SignUpRepository;
import com.prateek.reap.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.prateek.reap.util.CommonUtils.currentLoggedInUser;
import static com.prateek.reap.util.HtmlConstants.*;

@Controller
public class DashboardController {

    @Autowired
    private SignUpRepository signUpRepository;
    @Autowired
    private BadgeService badgeService;
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserStarCountService userStarCountService;
    @Autowired
    private UserStarReceivedService userStarReceivedService;

    @RequestMapping("/dashboard")
    public String getDashboardPage(Model model, Authentication authentication, Principal principal) {

        if (model.containsAttribute(KEY_SUCCESS))
            model.addAttribute(KEY_SUCCESS, VALUE_RECOGNITION_SUCCESS);

        if (model.containsAttribute(KEY_RECOGNITION_FAILURE))
            model.addAttribute(KEY_RECOGNITION_FAILURE, VALUE_RECOGNITION_FAILURE);

        if (model.containsAttribute(KEY_EMAIL_ERROR))
            model.addAttribute(KEY_EMAIL_ERROR, VALUE_RECOGNITION_RECIEVER_EMAIL);

        if (model.containsAttribute(KEY_SELF_ERROR))
            model.addAttribute(KEY_SELF_ERROR, VALUE_SELF_RECOGNITION_ERROR);

        model.addAttribute(KEY_LOGGED_IN_USER, signUpService.checkByEmail(authentication.getName()));
        model.addAttribute(KEY_BADGE, new BadgesGiven());
        model.addAttribute(KEY_USERS, userStarCountService.findAll());
        model.addAttribute(KEY_RECEIVE_STARS, userStarReceivedService.findByUserId(currentLoggedInUser(authentication)
                .getId()));
        model.addAttribute(KEY_STAR_COUNT , userStarCountService.findByUserId(currentLoggedInUser(authentication)
                .getId()));
        model.addAttribute(KEY_WALL_OF_FAME, badgeService.findAllByDate());
        model.addAttribute(KEY_NEWERS_BOARD, userStarReceivedService.findByTopNewers());
        return DASHBOARD_HTML_PAGE;
    }


    @RequestMapping("/get-all-user")
    @ResponseBody
    public List<User> getAllUsers() {
        return signUpService.getAllUser();
    }


    @RequestMapping(value = "/saveRecognition", method = RequestMethod.POST)
    public String saveRecognition(@RequestParam(REQUEST_PARAM_RECEIVER ) String receiverEmail,
                                  @RequestParam(REQUEST_PARAM_COMMENT) String comment, @RequestParam(REQUEST_PARAM_STAR) String starType,
                                  Authentication authentication, RedirectAttributes redirectAttributes)
            throws MessagingException {

        User user = signUpService.checkByEmail(receiverEmail);
        if (receiverEmail.equals(currentLoggedInUser(authentication).getEmail())) {
            redirectAttributes.addFlashAttribute(KEY_SELF_ERROR, VALUE_SELF_RECOGNITION_ERROR);
            return REDIRECT_TO_DASHBOARD;
        }
        if(starType.equals("Select Badges"))
        {
            redirectAttributes.addFlashAttribute(KEY_STAR_ERROR, VALUE_STAR_ERROR);
            return REDIRECT_TO_DASHBOARD;
        }
        if (comment.trim().equals("")) {
            redirectAttributes.addFlashAttribute(KEY_COMMENT_ERROR, VALUE_BLANK_COMMENT);
            return REDIRECT_TO_DASHBOARD;
        }

        if (receiverEmail.trim().equals(" ")) {
            redirectAttributes.addFlashAttribute(KEY_EMAIL_ERROR2, VALUE_RECOGNITION_RECIEVER_EMAIL);
            return REDIRECT_TO_DASHBOARD;
        }


        if (user == null) {
            redirectAttributes.addFlashAttribute(KEY_EMAIL_ERROR, VALUE_RECOGNITION_RECIEVER_EMAIL);
            return REDIRECT_TO_DASHBOARD;
        }

        boolean check = badgeService.saveRecognitionData(starType.toUpperCase(), receiverEmail,
                currentLoggedInUser(authentication).getEmail(), comment);

        if (!check) {
            redirectAttributes.addFlashAttribute(KEY_RECOGNITION_FAILURE, VALUE_RECOGNITION_FAILURE);
            return REDIRECT_TO_DASHBOARD;
        }

        redirectAttributes.addFlashAttribute(KEY_SUCCESS, VALUE_RECOGNITION_SUCCESS);

        return REDIRECT_TO_DASHBOARD;


    }

    @PostMapping("/getWall")
    @ResponseBody
    public List<BadgesGiven> getBadgesGiven(@RequestBody String name) {
        return badgeService.findAllByDateAndNameLike(name);
    }

    @RequestMapping(value = "/delete-recognition/{id}/{star}/{comment}", method = RequestMethod.GET)
    public String disableRecognition(
            @PathVariable Integer id, @PathVariable String star, @PathVariable String comment)
            throws MessagingException {
        comment = comment.replace("_", " ");
        badgeService.recognitionDelete(id, star, comment);
        return REDIRECT_TO_DASHBOARD;
    }

    @GetMapping(value = "/download.csv")
    public void download(@RequestParam(REQUEST_PARAM_STARTDATE) String s, @RequestParam(REQUEST_PARAM_END_DATE) String e,
                         HttpServletResponse response) throws IOException {
        response.setHeader(KEY_SET_HEADER_CSV, VALUE_SET_HEADER_CSV);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStart = LocalDateTime.parse(s, formatter);
        LocalDateTime dateEnd = LocalDateTime.parse(e, formatter);
        List<BadgesGiven> badgesGivens = badgeService.findAllBetween(dateStart, dateEnd).stream()
                .filter(e1 -> e1.isFlag()).collect(Collectors.toList());
        WriteDataToCsv.writeObjectToCSV(response.getWriter(), badgesGivens);
    }


    @PostMapping("/addRole")
    public String updateRole(@RequestParam(REQUEST_PARAM_EMAIL ) String email, @RequestParam( REQUEST_PARAM_ROLE) String role) {
        UserRole role1 = userRoleService.checkByName(role);
        User user = signUpService.checkByEmail(email);
        signUpService.allocateRole(role1, user);
        return REDIRECT_TO_DASHBOARD;
    }

    @PostMapping("/deleteRole")
    public String deleteRole(@RequestParam(REQUEST_PARAM_EMAIL ) String email, @RequestParam( REQUEST_PARAM_ROLE) String role) {
        UserRole role2 = userRoleService.checkByName(role);
        User user1 = signUpService.checkByEmail(email);
        signUpService.deleteRole(role2, user1);
        return REDIRECT_TO_DASHBOARD;
    }

    @PostMapping("/changePoints")
    public String changePoints(@RequestParam(REQUEST_PARAM_EMAIL ) String email, @RequestParam(REQUEST_PARAM_POINT) Integer points) {
        User user1 = signUpService.checkByEmail(email);
        signUpService.changePointsByAdmin(user1, points);
        return REDIRECT_TO_DASHBOARD;

    }


    @GetMapping("/searchRecognitionByDate/{start}/{end}")
    public String getUserRecodByName(@PathVariable(REQUEST_PARAM_START) String startDate, @PathVariable(REQUEST_PARAM_END) String endDate,
                                     Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStart = LocalDateTime.parse(startDate, formatter);
        LocalDateTime dateEnd = LocalDateTime.parse(endDate, formatter);
        List<BadgesGiven> recognitions = badgeService.findAllBetween(dateStart, dateEnd).stream()
                .filter(e1 -> e1.isFlag()).collect(Collectors.toList());
        model.addAttribute(KEY_WALL_OF_FAME, recognitions);
        return "dashboard::wallOfFame";
    }

    @PostMapping(value = "/updateActive")
    public String updateActive(@RequestParam("email") String email){

        User user = signUpService.checkByEmail(email);
        if(user.isActive()){
            user.setActive(false);
        }else {
            user.setActive(true);
        }
        signUpService.saveUser(user);
        return REDIRECT_TO_DASHBOARD;
    }


    @PostMapping("/changeGoldBadges")
    public String changeGoldBadges(@RequestParam(REQUEST_PARAM_EMAIL ) String email,
                          @RequestParam(REQUEST_PARAM_GOLD ) Integer goldStar) {
        User user1 = signUpService.checkByEmail(email);
        signUpService.changeGoldStarByAdmin(user1, goldStar);
        return REDIRECT_TO_DASHBOARD;

    }

    @PostMapping("/changeSilverBadges")
    public String changeSilverBadges(@RequestParam(REQUEST_PARAM_EMAIL ) String email,
                                   @RequestParam(REQUEST_PARAM_SILVER ) Integer silverStar) {
        User user1 = signUpService.checkByEmail(email);
        signUpService.changeSilverStarByAdmin(user1, silverStar);
        return REDIRECT_TO_DASHBOARD;

    }

    @PostMapping("/changeBronzeBadges")
    public String changeBronzeBadges(@RequestParam(REQUEST_PARAM_EMAIL ) String email,
                                   @RequestParam(REQUEST_PARAM_BRONZE ) Integer bronzeStar) {
        User user1 = signUpService.checkByEmail(email);
        signUpService.changeBronzeStarByAdmin(user1, bronzeStar);
        return REDIRECT_TO_DASHBOARD;

    }

}
