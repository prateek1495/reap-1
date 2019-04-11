package com.prateek.reap.controller;

import com.prateek.reap.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public String getLoginPage(Model model) {

        return "/login";

    }

    @RequestMapping("/login-error")
    public String getLoginErrorPage(Model model) {
        model.addAttribute("error", "Bad Credentials, Please Try Again");
        return "/login";

    }


}
