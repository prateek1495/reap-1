package com.prateek.reap.controller;

import com.prateek.reap.entity.User;
import com.prateek.reap.entity.UserRole;
import com.prateek.reap.service.SignUpService;
import com.prateek.reap.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SignupController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private UserRoleService userRoleSevice;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @RequestMapping("/signup")
    public String getSignUpPage(Model model) {

        if (model.asMap().containsKey("binding"))
            model.addAttribute("error", model.asMap().get("binding"));
        if (model.containsAttribute("exist"))
            model.addAttribute("exist", "Email Id Already Exists");

        model.addAttribute("user", new User());

        return "/signup";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)

    public String formSucess(@Valid @ModelAttribute("user") User responseData, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, @RequestParam("photo") MultipartFile file)
            throws IOException {

        UserRole userRole = userRoleSevice.checkByName("USER");
        ModelAndView modelAndView = new ModelAndView("/signup");
        List<User> emailVerification = (List<User>) signUpService.checkEmailAndActive(responseData.getEmail(), true);
        if (emailVerification.size() > 0) {
            redirectAttributes.addFlashAttribute("exist", "Email Id Already Exists");
            return "redirect:/signup";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("binding", bindingResult
                    .getFieldErrors()
                    .stream()
                    .map(f -> f.getField().toUpperCase() + " --> " + f.getDefaultMessage())
                    .collect(Collectors.joining(System.lineSeparator())));
            return "redirect:/signup";
        } else {
            signUpService.save(responseData, file);
            redirectAttributes.addFlashAttribute("signsuccess", "Registration Successfull,You can Login Now");
            return "redirect:/signup";
        }


    }

}
