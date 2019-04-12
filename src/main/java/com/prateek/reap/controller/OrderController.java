package com.prateek.reap.controller;

import com.prateek.reap.entity.User;
import com.prateek.reap.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.prateek.reap.util.CommonUtils.currentLoggedInUser;
import static com.prateek.reap.util.HtmlConstants.*;

@Controller
public class OrderController {

    @Autowired
    private UserStarReceivedService userStarReceivedService;
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private OrderService orderService;


    @RequestMapping("/items/{id}")
    public String getDashboardPage(Model model, @PathVariable(value = PATH_VARIABLE_ID) Integer id, Authentication authentication) {
        model.addAttribute(KEY_LOGGED_IN_USER , signUpService.checkByEmail(authentication.getName()));
        model.addAttribute(KEY_STAR_RECEIVED , userStarReceivedService.findByUserId(id));
        return ITEM_HTML_PAGE;
    }

    @PostMapping("/itemRedeem")
    @ResponseBody
    public String redeemPoints(@RequestParam(KEY_ITEMS ) String items, @RequestParam(KEY_TOTAL_PRICE ) String totalPrice, @RequestParam( KEY_ITEM_URLS ) String itemUrls, Authentication authentication) {
        User user = signUpService.checkByEmail(currentLoggedInUser(authentication).getEmail());
        return orderService.redeemPoints(items, totalPrice, itemUrls, user);


    }


}
