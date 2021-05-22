package ch.bittime.bittime.mvc.controller;


import ch.bittime.bittime.login.User;
import ch.bittime.bittime.login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;

/**
 * @author Pascal
 */

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping(value = {"/", "/login"})
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @GetMapping(value = "/admin/registration")
    public ModelAndView registration(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/admin/registration");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userData = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + userData.getUserName() + "/" + userData.getName() + " " + userData.getLastName() + " (" + userData.getEmail() + ")");
        return modelAndView;
    }


    @PostMapping(value = "/admin/registration")
    public ModelAndView createNewUser(User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/admin/registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("/admin/registration");

        }
        return modelAndView;
    }

    @GetMapping(value = "/admin/home")
    public ModelAndView adminHome() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());


        LocalTime time = LocalTime.now();
        System.out.println(time.getHour());


        if (time.getHour() >= 17 && time.getHour() <= 21) {
            modelAndView.addObject("adminMessage", "Good evening " + user.getName() + " " + user.getLastName() + " enjoy your after work beer :)");
            System.out.println(time.getHour());
        } else if (time.getHour() >= 22) {
            modelAndView.addObject("adminMessage", "It's late " + user.getName() + " " + user.getLastName() + " better go to bed, Good night");
            System.out.println(time.getHour());
        } else if (time.getHour() >= 6 && time.getHour() <= 9) {
            modelAndView.addObject("adminMessage", "Good Morning " + user.getName() + " " + user.getLastName() + " have a nice day and do some good work");
            System.out.println(time.getHour());
        } else if (time.getHour() >= 10 && time.getHour() <= 16) {
            modelAndView.addObject("adminMessage", "Good Day " + user.getName() + " " + user.getLastName() + " still here working, huh?");
            System.out.println(time.getHour());
        }

        modelAndView.addObject("userName", "Welcome " + user.getUserName() +
                "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");

        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

//    @GetMapping(value = "/admin/userPanel")
//    public String showUserPanel(){
//        return "/admin/userPanel";
//    }


    @GetMapping(value = "/user/home")
    public ModelAndView showUserHome() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());

        LocalTime time = LocalTime.now();


        if (time.getHour() >= 17 && time.getHour() <= 21) {
            modelAndView.addObject("userMessage", "Good evening " + user.getName() + " " + user.getLastName() + " enjoy your after work beer :)");
            System.out.println(time.getHour());
        } else if (time.getHour() >= 22) {
            modelAndView.addObject("userMessage", "It's late " + user.getName() + " " + user.getLastName() + " better go to bed, Good night");
            System.out.println(time.getHour());
        } else if (time.getHour() >= 6 && time.getHour() <= 9) {
            modelAndView.addObject("userMessage", "Good Morning " + user.getName() + " " + user.getLastName() + " have a nice day and do some good work");
            System.out.println(time.getHour());
        } else if (time.getHour() >= 10 && time.getHour() <= 16) {
            modelAndView.addObject("userMessage", "Good Day " + user.getName() + " " + user.getLastName() + " still here working, huh?");
            System.out.println(time.getHour());
        }

        modelAndView.addObject("userName", "Welcome " + user.getUserName() +
                "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");


        return modelAndView;
    }

    @GetMapping(value = "/default")
    public String defaultAfterLogin(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        if (role.contains("ADMIN")) {
            System.out.println("default Login Method");
            return "redirect:/admin/home/";
        }
        return "redirect:/user/home";
    }
}
