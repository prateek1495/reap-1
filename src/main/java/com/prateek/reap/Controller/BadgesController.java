package com.prateek.reap.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BadgesController {

    @RequestMapping("/badges")
    public String getLoginPage(Model model) {

        return "dashboard/badges";

    }
}
