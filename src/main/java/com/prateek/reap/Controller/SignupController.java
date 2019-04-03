package com.prateek.reap.Controller;

import com.prateek.reap.Entity.User;
import com.prateek.reap.Entity.UserRole;
import com.prateek.reap.Service.SignUpService;
import com.prateek.reap.Service.UserRoleService;
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

import static com.vagish.reap.util.HtmlConstants.*;

@Controller
public class SignupController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private UserRoleService userRoleSevice;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


   /* @RequestMapping("/signup")
    public String getSignUpPage(Model model) {

        if (model.asMap().containsKey("binding"))
            model.addAttribute("error", model.asMap().get("binding"));
        if (model.containsAttribute("emailError"))
            model.addAttribute("emailError", "Email Id Already Exists");

        model.addAttribute("user", new User());
        return "auth/signUp";
    }
*/

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

    public String formSucess(@Valid @ModelAttribute("user") User responseData, BindingResult bindingResult, RedirectAttributes redirectAttributes,@RequestParam("photo") MultipartFile file) throws IOException {

        UserRole userRole = userRoleSevice.checkByName("USER");
        ModelAndView modelAndView = new ModelAndView("/signup");
        List<User> emailVerification = (List<User>) signUpService.checkEmailAndActive(responseData.getEmail(), true);
        System.out.println(emailVerification);
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

           /* responseData.setFirstName(responseData.getFirstName());
            responseData.setLastName(responseData.getLastName());
            responseData.setEmail(responseData.getEmail());
            responseData.getRoles().add(userRole);

            responseData.setPassword(bCryptPasswordEncoder.encode(responseData.getPassword()));

            responseData.setPassword(responseData.getPassword());

            responseData.setActive(true);
//          responseData.setImageUrl(signUpService.saveImagePath(responseData.getImageUrl()));
//            responseData.setImageUrl(signUpService.saveImagePath("file"));
            responseData.setImageUrl(signUpService.saveImagePath(file));

            signUpService.saveUser(responseData);*/

            signUpService.save(responseData,file);

            redirectAttributes.addFlashAttribute("signsuccess", "Registration Successfull,You can Login Now");
            return "redirect:/signup";


        }





      /*  ModelAndView modelAndView= new ModelAndView();
        List<User> userCheck = signUpService.checkUser(responseData);


        if (!(userCheck.size()>0)) {
            String pwd=responseData.getPassword();
            String encryptPwd=bCryptPasswordEncoder.encode(pwd);
            responseData.setPassword(encryptPwd);
            String pathOfImage = signUpService.saveImagePath(file);
            responseData.setImageUrl(pathOfImage);
            signUpService.saveUser(responseData);
            return modelAndView;
        }
        else
        {
            return modelAndView.addObject("error","username already exists");
            //System.out.println("Username exists");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("binding", bindingResult
                    .getFieldErrors()
                    .stream()
                    .map(f -> f.getField().toUpperCase() + " --> " + f.getDefaultMessage())
                    .collect(Collectors.joining(System.lineSeparator())));
            return "redirect:/signup";
        }

        user.getRoles().add(userRole);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setImageUrl(userService.saveImagePath(user.getImageUrl()));
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("sign-success", "Sign-in Successful");
        return "redirect:/login";*/

    }

}
