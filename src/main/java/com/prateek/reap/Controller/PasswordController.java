package com.prateek.reap.Controller;

import com.prateek.reap.Entity.User;
import com.prateek.reap.Service.EmailService;
import com.prateek.reap.Service.LoginService;
import com.prateek.reap.Service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    public String getResetPasswordPage(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "auth/resetPassword";
    }


    @RequestMapping(value = "/forget-password-process", method = RequestMethod.POST)
    public String sendPasswordRestMail(@RequestParam String email, RedirectAttributes redirectAttributes) {


        User user = signUpService.checkByEmail(email);
        if (user==null) {
            redirectAttributes.addFlashAttribute("error", "Email-id entered is wrong, Please enter correct id.");
            return "redirect:/forget-password";
        }

        String resetUrl = signUpService.saveTokenAndGenerateResetUrl(user,"http://localhost:8080/reset-password?token=" );

        emailService.sendEmailToSingleRecipient(email,"Reset Password", resetUrl);
        redirectAttributes.addFlashAttribute("passwordsuccess", "Email sent Successfully");

        return "redirect:/login";
    }



    @RequestMapping(value = "/reset-password-process", method = RequestMethod.POST)
    public String resetPasswordProcess(@RequestParam Map<String, String> requestParams, RedirectAttributes redirectAttributes) {

        User user = signUpService.findUserByResetToken(requestParams.get("token"));
        /* User user1=user.get();*/
        user.setToken(null);
        user.setActive(true);

        user.setPassword(bCryptPasswordEncoder.encode(requestParams.get("newPassword")));

        signUpService.saveUser(user);

        redirectAttributes.addFlashAttribute("resetPassword", "Password Resetted, Please Log in.");
        return "redirect:/login";
    }


    // Display forgotPassword page
    /*@RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public ModelAndView displayForgotPasswordPage() {
        return new ModelAndView("forgotPassword");
    }

    // Process form submission from forgotPassword page
    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ModelAndView processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("email") String userEmail, HttpServletRequest request) {

        // Lookup user in database by e-mail
        Optional<User> optional = loginService.findUserByEmail(userEmail);

        if (!optional.isPresent()) {
            modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
        } else {

            // Generate random 36-character string token for reset password
            User user = optional.get();
            user.setToken(UUID.randomUUID().toString());

            // Save token to database
            signUpService.saveUser(user);

            String appUrl = request.getScheme() + "://" + request.getServerName();

            // Email message
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("support@demo.com");
            passwordResetEmail.setTo(user.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + "/reset?token=" + user.getToken());

            emailService.sendEmail(passwordResetEmail);

            // Add success message to view
            modelAndView.addObject("successMessage", "A password reset link has been sent to " + userEmail);
        }

        modelAndView.setViewName("forgotPassword");
        return modelAndView;

    }

    // Display form to reset password
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {

        Optional<User> user = loginService.findUserByResetToken(token);

        if (user.isPresent()) { // Token found in DB
            modelAndView.addObject("resetToken", token);
        } else { // Token not found in DB
            modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
        }

        modelAndView.setViewName("resetPassword");
        return modelAndView;
    }

    // Process reset password form
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

        // Find the user associated with the reset token
        Optional<User> user = loginService.findUserByResetToken(requestParams.get("token"));

        // This should always be non-null but we check just in case
        if (user.isPresent()) {

            User resetUser = user.get();

            // Set new password
            resetUser.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));

            // Set the reset token to null so it cannot be used again
            resetUser.setToken(null);

            resetUser.setActive(false);
            // Save user
            signUpService.saveUser(resetUser);

            // In order to set a model attribute on a redirect, we must use
            // RedirectAttributes
            redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");

            modelAndView.setViewName("redirect:login");
            return modelAndView;

        } else {
            modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
            modelAndView.setViewName("resetPassword");
        }

        return modelAndView;
    }

    // Going to reset page without a token redirects to login page
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
        return new ModelAndView("redirect:login");
    }
*/
}
