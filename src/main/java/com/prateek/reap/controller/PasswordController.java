package com.prateek.reap.controller;

import com.prateek.reap.entity.User;
import com.prateek.reap.service.EmailService;
import com.prateek.reap.service.LoginService;
import com.prateek.reap.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;


@Controller
public class PasswordController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @RequestMapping("/forget-password")
    public String getPasswordResetPage(Model model) {
        if (model.containsAttribute("error"))
            model.addAttribute("error", "Please Enter Correct Email Id");
        return "auth/forgetPassword";
    }

    @RequestMapping(value = "/reset-password", method = RequestMethod.GET)
    public String getResetPasswordPage(@RequestParam String token, Model model, RedirectAttributes redirectAttributes) {
        User user = signUpService.findByToken(token);
        if (user == null) {
            redirectAttributes.addFlashAttribute("expire", "Reset Password Link is expired");
            return "redirect:/login";
        }
        model.addAttribute("token", token);
        return "auth/resetPassword";
    }


    @RequestMapping(value = "/forget-password-process", method = RequestMethod.POST)
    public String sendPasswordRestMail(@RequestParam String email, RedirectAttributes redirectAttributes) {


        User user = signUpService.checkByEmail(email);
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "Email-id entered is wrong, Please enter correct id.");
            return "redirect:/forget-password";
        }

        String resetUrl = signUpService.saveTokenAndGenerateResetUrl(user, "http://localhost:8080/reset-password?token=");

        emailService.sendEmailToSingleRecipient(email, "Reset Password", resetUrl);
        redirectAttributes.addFlashAttribute("passwordsuccess", "Email sent Successfully");

        return "redirect:/login";
    }


    @RequestMapping(value = "/reset-password-process", method = RequestMethod.POST)
    public String resetPasswordProcess(@RequestParam Map<String, String> requestParams, RedirectAttributes redirectAttributes) {
        User user1 = signUpService.findByToken(requestParams.get("token"));
        if (user1 == null) {
            redirectAttributes.addFlashAttribute("expire", "Reset Password Link is expired");
            return "redirect:/login";
        }


        signUpService.resetEmailTokenAndSetNewPassword(
                requestParams.get("token"), requestParams.get("newPassword"));

        redirectAttributes.addFlashAttribute("resetPassword", "Password Resetted, Please Log in.");
        return "redirect:/login";
    }


}
