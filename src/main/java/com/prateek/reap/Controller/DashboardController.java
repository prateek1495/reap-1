package com.prateek.reap.Controller;

import com.prateek.reap.Entity.BadgesGiven;
import com.prateek.reap.Entity.User;
import com.prateek.reap.Service.BadgeService;
import com.prateek.reap.Service.UserRoleService;
import com.prateek.reap.Service.UserStarCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.prateek.reap.util.CommonUtils.currentLoggedInUser;

@Controller
public class DashboardController {



    @Autowired
    private BadgeService badgeService;

   /* @Autowired
    private  CsvService csvService;
*/
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private  UserStarCountService userStarCountService;

  /*  @RequestMapping("/dashboard")
    public ModelAndView getDashboard()
    {
        ModelAndView modelAndView = new ModelAndView("auth/dashboard");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }*/

    @RequestMapping("/dashboard")
    public String getDashboardPage(Model model, Authentication authentication) {

        if (model.containsAttribute("success"))
            model.addAttribute("success", "Newer Has Been Successfully RECOGNIZED");

        if (model.containsAttribute("saveError"))
            model.addAttribute("saveError", "Cannot Give Star To Newer");

        if (model.containsAttribute("emailError"))
            model.addAttribute("emailError", "User Doesn't exists");

        if (model.containsAttribute("selfRecoError"))
            model.addAttribute("selfRecoError", "Cannot Give To Recognition to yourself");

        model.addAttribute("loggedUser",currentLoggedInUser(authentication));
        model.addAttribute("recog", badgeService.findAllActiveRecognition(true));
        model.addAttribute("badge", new BadgesGiven());
        model.addAttribute("users", userStarCountService.findAll());

        return "auth/dashboard";
    }

}
