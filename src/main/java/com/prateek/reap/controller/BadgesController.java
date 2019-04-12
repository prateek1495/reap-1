package com.prateek.reap.controller;

import com.prateek.reap.entity.BadgesGiven;
import com.prateek.reap.service.BadgeService;
import com.prateek.reap.service.OrderService;
import com.prateek.reap.service.SignUpService;
import com.prateek.reap.service.UserStarReceivedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

import static com.prateek.reap.util.CommonUtils.currentLoggedInUser;
import static com.prateek.reap.util.HtmlConstants.*;

@Controller
public class BadgesController {

    @Autowired
    private SignUpService signUpService;
    @Autowired
    private BadgeService badgeService;

    @Autowired
    private UserStarReceivedService userStarReceivedService;

    @Autowired
    private OrderService orderService;


    @RequestMapping("/badge/{id}")
    public String getPage(Model model, @PathVariable(value = PATH_VARIABLE_ID ) Integer id, Authentication authentication) {
        List<BadgesGiven> allRecognition=badgeService.findAllRecognitionByUser(id).stream().filter(e1 -> e1.isFlag()).collect(Collectors.toList());
        model.addAttribute(KEY_LOGGED_IN_USER, signUpService.checkByEmail(authentication.getName()));
        model.addAttribute(THYMELEAF_ALL_SHARED_RECOGNITION, badgeService.findAllSharedRecognition(id));
        model.addAttribute(THYMELEAF_ALL_RECIEVED_RECOGNITION, badgeService.findAllReceivedRecognition(id));
        model.addAttribute(THYMELEAF_ALL_RECOGNITION, allRecognition);
        model.addAttribute(THYMELEAF_STAR_RECEIVED , userStarReceivedService.findByUserId(id));
        model.addAttribute(THYMELEAF_PURCHASE_HISTORY,orderService.findAllOrders(id));
        return BADGE_HTML_PAGE;

    }


}











