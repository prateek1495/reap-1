package com.prateek.reap.Controller;

import com.prateek.reap.Entity.BadgesGiven;
import com.prateek.reap.Entity.ResponseDto;
import com.prateek.reap.Entity.User;
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
import java.util.List;

import static com.prateek.reap.util.CommonUtils.currentLoggedInUser;
import static com.vagish.reap.util.HtmlConstants.KEY_SET_CONTENT_TYPE_CSV;
import static com.vagish.reap.util.HtmlConstants.KEY_SET_HEADER_CSV;
import static com.vagish.reap.util.HtmlConstants.VALUE_SET_HEADER_CSV;

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
    private CsvService csvService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserStarCountService userStarCountService;
    @Autowired
    private UserStarReceivedService userStarReceivedService;

  /*  @RequestMapping("/dashboard")
    public ModelAndView getDashboard()
    {
        ModelAndView modelAndView = new ModelAndView("auth/dashboard");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }*/

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

//        User id = signUpRepository.findByEmail(authentication.getName());
        model.addAttribute("loggedUser", currentLoggedInUser(authentication));
//        model.addAttribute("recog", badgeService.findAllActiveRecognition(true));
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
            @PathVariable int id, @PathVariable String star, @PathVariable String comment) {
        badgeService.recognitionDelete(id, star, comment);

        return "redirect:/dashboard";
    }

    @RequestMapping("/list-badges")

    public String wallOfFameList(Model model) {

        model.addAttribute("wall", badgeService.findAllByDate());
        return "redirect:/dashboard";

    }

    @RequestMapping("/download-csv")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=users.csv");
        csvService.getCsv(response.getWriter(), badgeService.findAll());
    }
}
