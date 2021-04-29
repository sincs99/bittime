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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }



    @RequestMapping(value = "/admin/registration", method = RequestMethod.GET)
    public ModelAndView registration(Model model){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/admin/registration");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userData = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + userData.getUserName() + "/" + userData.getName() + " " + userData.getLastName() + " (" + userData.getEmail() + ")");
        return modelAndView;
    }




    @RequestMapping(value = "/admin/registration", method = RequestMethod.POST)
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

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView adminHome(){



        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());



        LocalTime time = LocalTime.now();


        if (time.getHour() > (17) && time.getHour()< (22)){
            modelAndView.addObject("adminMessage", "Good evening " +user.getName()+" " +user.getLastName() + " enjoy your after work beer :)");
        } else if (time.getHour() > (22)){
            modelAndView.addObject("adminMessage", "It's late " +user.getName()+" " +user.getLastName() + " better go to bed, Good night");
        } else if (time.getHour() > (6) && time.getHour()< (9)){
            modelAndView.addObject("adminMessage", "Good Morning " +user.getName()+" " +user.getLastName() + " have a nice day and do some good work");
        }else if(time.getHour() > (10) && time.getHour()< (16)) {
            modelAndView.addObject("adminMessage", "Good Day " +user.getName()+" " +user.getLastName()+ " still here working, huh?") ;
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
    public  ModelAndView showUserHome(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());

        LocalTime time = LocalTime.now();


        if (time.getHour() > (17) && time.getHour()< (22)){
            modelAndView.addObject("userMessage", "Good evening " +user.getName()+" " +user.getLastName() + " enjoy your after work beer :)");
        } else if (time.getHour() > (22) && time.getHour()< (6)){
            modelAndView.addObject("userMessage", "It's late " +user.getName()+" " +user.getLastName() + " better go to bed, Good night");
        } else if (time.getHour() > (6) && time.getHour()< (10)){
            modelAndView.addObject("userMessage", "Good Morning " +user.getName()+" " +user.getLastName() + " have a nice day");
        }else {
            modelAndView.addObject("userMessage", "Good Day " +user.getName()+" " +user.getLastName());
        }
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");




        return modelAndView;
    }

    @GetMapping(value = "/default")
    public  String defaultAfterLogin(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        if (role.contains("ADMIN")){
            System.out.println("default Login Method");
            return "redirect:/admin/home/";
        }
        return "redirect:/user/home";
    }
}
