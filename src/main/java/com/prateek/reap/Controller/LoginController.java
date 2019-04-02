package com.prateek.reap.Controller;

import com.prateek.reap.Entity.User;
import com.prateek.reap.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

   /* @RequestMapping("/login")
    public ModelAndView signUp() {
        ModelAndView modelAndView = new ModelAndView("auth/login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }*/


   @RequestMapping("/login")
    public String getLoginPage(Model model) {

       return "auth/login";

   }

    @RequestMapping("/login-error")
    public String getLoginErrorPage(Model model) {
        model.addAttribute("error", "Bad Credentials, Please Try Again");
        return  "auth/login";

    }

















       /* if (model.containsAttribute("sign-success"))
            model.addAttribute("signSuccess", "Sign in Successful, Please Log in.");

        if (model.containsAttribute("forgetPassword"))
            model.addAttribute("forgetPassword", "Email Sent Successfully");

        if (model.containsAttribute("resetPassword"))
            model.addAttribute("resetPassword", "Password Reseted, Please Log in");
*/





   /* @RequestMapping(value = "/login", method = RequestMethod.GET)
    ModelAndView getLogin() {
        return new ModelAndView("auth/login").addObject("login", new User());
    }
*/

   /* @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginProcessing(@ModelAttribute("login") User userResponse) {
        ModelAndView modelAndView = new ModelAndView("auth/login");
        System.out.println(userResponse.getEmail());
        Optional<User> user = loginService.validateUser(userResponse);
        return modelAndView;

    }*/

}
