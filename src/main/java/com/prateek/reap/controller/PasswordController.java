package com.prateek.reap.controller;

import com.prateek.reap.entity.User;
import com.prateek.reap.service.EmailService;
import com.prateek.reap.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import java.util.Map;

import static com.prateek.reap.util.Constants.*;
import static com.prateek.reap.util.HtmlConstants.*;


@Controller
public class PasswordController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @RequestMapping("/forget-password")
    public String getPasswordResetPage(Model model) {
        if (model.containsAttribute(KEY_ERROR ))
            model.addAttribute(KEY_ERROR , VALUE_WRONG_EMAIL_ENTERED );
        return FORGET_PASSWORD_HTML_PAGE;
    }

    @RequestMapping(value = "/reset-password", method = RequestMethod.GET)
    public String getResetPasswordPage(@RequestParam String token, Model model, RedirectAttributes redirectAttributes) {
        User user = signUpService.findByToken(token);
        if (user == null) {
            redirectAttributes.addFlashAttribute(KEY_LINK_EXPIRE, VALUE_RESET_PASSWORD_ERROR );
            return REDIRECT_TO_LOGIN;
        }
        model.addAttribute(KEY_TOKEN, token);
        return RESET_PASSWORD_HTML_PAGE ;
    }


    @RequestMapping(value = "/forget-password-process", method = RequestMethod.POST)
    public String sendPasswordRestMail(@RequestParam String email, RedirectAttributes redirectAttributes)
            throws MessagingException {


        User user = signUpService.checkByEmail(email);
        if (user == null) {
            redirectAttributes.addFlashAttribute(KEY_ERROR , VALUE_WRONG_EMAIL_ENTERED);
            return REDIRECT_TO_FORGET_PASSWORD;
        }

        String resetUrl = signUpService.saveTokenAndGenerateResetUrl(user, RESET_TOKEN_URL );

        emailService.sendEmailToSingleRecipient(email, MAIL_SUBJECT , resetUrl);
        redirectAttributes.addFlashAttribute(KEY_PASSWORD_SUCCESS, VALUE_FORGET_PASSWORD_SUCCESS);

        return REDIRECT_TO_LOGIN;
    }


    @RequestMapping(value = "/reset-password-process", method = RequestMethod.POST)
    public String resetPasswordProcess(@RequestParam Map<String, String> requestParams, RedirectAttributes redirectAttributes) {
        User user1 = signUpService.findByToken(requestParams.get(KEY_TOKEN));
        if (user1 == null) {
            redirectAttributes.addFlashAttribute(KEY_LINK_EXPIRE, VALUE_RESET_PASSWORD_ERROR);
            return REDIRECT_TO_LOGIN;
        }

        signUpService.resetEmailTokenAndSetNewPassword(requestParams.get(KEY_TOKEN), requestParams.get(KEY_NEW_PASSWORD));
        redirectAttributes.addFlashAttribute(KEY_RESET_PASSWORD_SUCCESS , VALUE_RESET_PASSWORD_SUCCESS);
        return REDIRECT_TO_LOGIN;
    }


}
