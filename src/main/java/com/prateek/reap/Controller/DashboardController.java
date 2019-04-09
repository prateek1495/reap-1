package com.prateek.reap.Controller;

import com.prateek.reap.Entity.BadgesGiven;
import com.prateek.reap.Entity.ResponseDto;
import com.prateek.reap.Entity.User;
import com.prateek.reap.Entity.UserRole;
import com.prateek.reap.Repository.SignUpRepository;
import com.prateek.reap.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.prateek.reap.util.CommonUtils.currentLoggedInUser;

@Controller
public class DashboardController {

    @Autowired
    SignUpRepository signUpRepository;
    @Autowired
    private ResponseDtoService responseDtoService;
    @Autowired
    private BadgeService badgeService;
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserStarCountService userStarCountService;
    @Autowired
    private UserStarReceivedService userStarReceivedService;

    @RequestMapping("/dashboard")
    public String getDashboardPage(Model model, Authentication authentication) {

        if (model.containsAttribute("success"))
            model.addAttribute("success", "Newer Has Been Successfully RECOGNIZED");

        if (model.containsAttribute("saveError"))
            model.addAttribute("saveError", "Cannot Give Star To Newer");

        if (model.containsAttribute("emailError"))
            model.addAttribute("emailError", "User Doesn't exists");

        if (model.containsAttribute("selfRecoError"))
            model.addAttribute("selfRecoError", "Cannot Give To Recognition to yourself");

        model.addAttribute("loggedUser", currentLoggedInUser(authentication));
        model.addAttribute("badge", new BadgesGiven());
        model.addAttribute("users", userStarCountService.findAll());
        model.addAttribute("recvstars", userStarReceivedService.findByUserId(currentLoggedInUser(authentication)
                .getId()));
        model.addAttribute("starCount", userStarCountService.findByUserId(currentLoggedInUser(authentication)
                .getId()));
        System.out.println(userStarReceivedService.findByUserId(currentLoggedInUser(authentication)
                .getId()));
        model.addAttribute("wall", badgeService.findAllByDate());
        System.out.println(userStarReceivedService.findByTopNewers());
        model.addAttribute("newersb", userStarReceivedService.findByTopNewers());
        return "/dashboard";
    }

    @RequestMapping("/current-user")
    @ResponseBody
    public ResponseDto getCurrentUser(Authentication authentication) {
        return responseDtoService.getUser(authentication);
    }

    @RequestMapping("/get-all-user")
    @ResponseBody
    public List<User> getAllUsers() {

        System.out.println(signUpService.getAllUser());
        return signUpService.getAllUser();
    }


    @RequestMapping(value = "/saveRecognition", method = RequestMethod.POST)
    public String saveRecognition(@RequestParam("receiver") String receiverEmail, @RequestParam("comment") String comment, @RequestParam("star") String starType, Authentication authentication, RedirectAttributes redirectAttributes) {

        User user = signUpService.checkByEmail(receiverEmail);
        if (receiverEmail.equals(currentLoggedInUser(authentication).getEmail())) {
            redirectAttributes.addFlashAttribute("selfRecoError", "Cannot Give Recognition to yourself");
            return "redirect:/dashboard";
        }

        if (user == null) {
            redirectAttributes.addFlashAttribute("emailError", "User Doesnot exist");
            return "redirect:/dashboard";
        }

        System.out.println(starType);

        boolean check = badgeService.saveRecognitionData(starType.toUpperCase(), receiverEmail, currentLoggedInUser(authentication).getEmail(), comment);

        if (!check) {
            redirectAttributes.addFlashAttribute("saveError", "Cannot Give a Star");
            return "redirect:/dashboard";
        }

        redirectAttributes.addFlashAttribute("success", " Successfully RECOGNIZED");

        return "redirect:/dashboard";


    }

    @PostMapping("/getWall")
    @ResponseBody
    public List<BadgesGiven> getBadgesGiven(@RequestBody String name) {
        System.out.println(name);
        return badgeService.findAllByDateAndNameLike(name);
    }

    @RequestMapping(value = "/delete-recognition/{id}/{star}/{comment}", method = RequestMethod.GET)

    public String disableRecognition(
            @PathVariable Integer id, @PathVariable String star, @PathVariable String comment) {
        System.out.println(comment);
        comment = comment.replace("_", " ");
        badgeService.recognitionDelete(id, star, comment);

        return "redirect:/dashboard";
    }

    @GetMapping(value = "/download.csv")
    public void download(@RequestParam("startDate") String s, @RequestParam("endDate") String e, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment; file=BadgesGiven.csv");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStart = LocalDateTime.parse(s, formatter);
        LocalDateTime dateEnd = LocalDateTime.parse(e, formatter);
        List<BadgesGiven> badgesGivens = badgeService.findAllBetween(dateStart, dateEnd);
        WriteDataToCsv.writeObjectToCSV(response.getWriter(), badgesGivens);
    }


    @PostMapping("/addRole")
    public String updateRole(@RequestParam("email") String email, @RequestParam("role") String role, Model model) {
        UserRole role1 = userRoleService.checkByName(role);
        User user = signUpService.checkByEmail(email);
        signUpService.allocateRole(role1, user);
        return "redirect:/dashboard";
    }

    @PostMapping("/deleteRole")
    public String deleteRole(@RequestParam("email") String email, @RequestParam("role") String role, Model model) {
        UserRole role2 = userRoleService.checkByName(role);
        User user1 = signUpService.checkByEmail(email);
        signUpService.deleteRole(role2, user1);
        return "redirect:/dashboard";
    }

    @PostMapping("/changePoints")
    public String changePoints(@RequestParam("email") String email, @RequestParam("point") Integer points, Model model) {
        User user1 = signUpService.checkByEmail(email);
        signUpService.changePoints(user1, points);
        return "redirect:/dashboard";

    }

    @RequestMapping("/deactivate-user")
    @ResponseBody
    public void userDeactivate(@RequestParam("userId") int id) {
        signUpService.deactivateUser(id);
    }

    @RequestMapping("/activate-user")
    @ResponseBody
    public void userActivate(@RequestParam("userId") int id) {
        signUpService.activateUser(id);
    }


}
