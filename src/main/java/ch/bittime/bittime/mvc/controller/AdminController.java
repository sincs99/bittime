package ch.bittime.bittime.mvc.controller;

/**
 * @author Pascal
 */

import ch.bittime.bittime.login.User;
import ch.bittime.bittime.login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ch.bittime.bittime.login.repository.UserRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

//    @RequestMapping(value = "admin/deleteUser", method = RequestMethod.DELETE)
//    public String deleteUser(Model model){
//        List<User> listUser =userRepo.finb
//    }


    @GetMapping("/admin/userPanel")
    public String listUser(Model model){
        List<User> listUser = userRepo.findAll();
        model.addAttribute("listUser", listUser);
        System.out.println(listUser);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");

        return "/admin/userPanel";

    }

    @GetMapping("/admin/timeRecording")
    public String timeRecording(){
        return "/admin/timeRecording";
    }

    @GetMapping("/admin/reportingView")
    public String reportingView(){
        return "/admin/reportingView";
    }

    @GetMapping("/admin/vacationManagement")
    public String vacationManagement(){
        return "/admin/vacationManagement";
    }

    @GetMapping("/admin/profileView")
    public String profileView(){
        return "/admin/profileView";
    }



}
