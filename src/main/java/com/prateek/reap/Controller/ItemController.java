package com.prateek.reap.Controller;

import com.prateek.reap.Service.BadgeService;
import com.prateek.reap.Service.ItemService;
import com.prateek.reap.Service.UserStarCountService;
import com.prateek.reap.Service.UserStarReceivedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.prateek.reap.util.CommonUtils.currentLoggedInUser;

@Controller
public class ItemController {

    @Autowired
    UserStarCountService userStarCountService;
    @Autowired
    UserStarReceivedService userStarReceivedService;
    @Autowired
    BadgeService badgeService;
    @Autowired
    ItemService itemService;


    @RequestMapping("/items/{id}")
    public String getDashboardPage(Model model, @PathVariable(value = "id") Integer id, Authentication authentication) {
        model.addAttribute("loggedUser", currentLoggedInUser(authentication));
        model.addAttribute("starCount", userStarReceivedService.findByUserId(id));
        model.addAttribute("items",itemService.findAll());
        return "/item";
    }


}
