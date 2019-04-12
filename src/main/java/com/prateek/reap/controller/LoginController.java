package com.prateek.reap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.prateek.reap.util.HtmlConstants.*;


@Controller
public class LoginController {

    @RequestMapping("/login")
    public String getLoginPage() {

        return LOG_IN_HTML_PAGE ;

    }

    @RequestMapping("/login-error")
    public String getLoginErrorPage(Model model) {
        model.addAttribute(HTML_ERROR_PAGE, VALUE_LOGIN_ERROR );
        return LOG_IN_HTML_PAGE ;

    }


}
