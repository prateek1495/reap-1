package com.prateek.reap.controller;

import com.prateek.reap.entity.User;
import com.prateek.reap.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.prateek.reap.util.CommonUtils.currentLoggedInUser;

@Controller
public class OrderController {

    @Autowired
    private UserStarCountService userStarCountService;
    @Autowired
    private UserStarReceivedService userStarReceivedService;
    @Autowired
    private BadgeService badgeService;
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private OrderService orderService;


    @RequestMapping("/items/{id}")
    public String getDashboardPage(Model model, @PathVariable(value = "id") Integer id, Authentication authentication) {
        model.addAttribute("loggedUser", currentLoggedInUser(authentication));
        model.addAttribute("starCount", userStarReceivedService.findByUserId(id));
        return "/item";
    }

    @PostMapping("/itemRedeem")
    @ResponseBody
    public String redeemPoints(@RequestParam("items") String items, @RequestParam("totalPrice") String totalPrice, @RequestParam("itemUrls") String itemUrls, Authentication authentication) {
        Integer cartPrice = Integer.parseInt(totalPrice);
        User user = signUpService.checkByEmail(currentLoggedInUser(authentication).getEmail());
        return orderService.redeemPoints(items, cartPrice, itemUrls, user);


    }


}
