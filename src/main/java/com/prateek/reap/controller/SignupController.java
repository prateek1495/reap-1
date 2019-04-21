package com.prateek.reap.controller;

import com.prateek.reap.entity.User;
import com.prateek.reap.entity.UserRole;
import com.prateek.reap.service.SignUpService;
import com.prateek.reap.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.prateek.reap.util.HtmlConstants.*;

@Controller
public class SignupController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private UserRoleService userRoleSevice;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    Logger logger= LoggerFactory.getLogger(SignupController.class);

    @RequestMapping("/signup")
    public String getSignUpPage(Model model) {
        logger.info("Someone is on signup page");
        if (model.containsAttribute(KEY_EMAIL_EXISTS_ERROR))
            model.addAttribute(KEY_EMAIL_EXISTS_ERROR, VALUE_EMAIL_EXISTS_ERROR);

        model.addAttribute(KEY_USER, new User());

        return SIGN_UP_HTML_PAGE ;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String formSucess(@Valid @ModelAttribute(KEY_USER) User responseData, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, @RequestParam(REQUEST_PARAM_PROFILE_IMAGE) MultipartFile file,Model model)
            throws IOException {
        if (bindingResult.hasErrors()) {
            logger.error("Error with Binding Object"+bindingResult);
            List<String> fieldErrors = new ArrayList<>();
            for(FieldError field : bindingResult.getFieldErrors() )
            {
                fieldErrors.add(field.getDefaultMessage());
            }
            model.addAttribute(KEY_BINDING_RESULT ,fieldErrors);
            return SIGN_UP_HTML_PAGE ;
        }

        UserRole userRole = userRoleSevice.checkByName("USER");
        List<User> emailVerification = (List<User>) signUpService.checkEmailAndActive(responseData.getEmail(), true);
        if (emailVerification.size() > 0) {
            logger.error("Email Id already exist"+responseData.getEmail());
            redirectAttributes.addFlashAttribute(KEY_EMAIL_EXISTS_ERROR, VALUE_EMAIL_EXISTS_ERROR);
            return REDIRECT_TO_SIGN_UP;
        }

        else {
            signUpService.save(responseData, file);
            logger.info("User saved"+responseData.getEmail());
            redirectAttributes.addFlashAttribute(KEY_SIGN_IN_SUCCESS , VALUE_SIGN_UP_SUCCESS);
            return REDIRECT_TO_SIGN_UP;
        }


    }

}
