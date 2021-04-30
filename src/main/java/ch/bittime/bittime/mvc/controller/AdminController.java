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
import org.springframework.web.bind.annotation.*;
import ch.bittime.bittime.login.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;



    @RequestMapping("/admin/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") int id, Model model) {

        System.out.println("Request ok");
        Optional<User> i = userService.findUserById(id);
        System.out.println(i);
/**
 *
 * @TODO: 30.04.2021 auskommentieren entfernen! @Pascal
 */

        //userService.deleteUser(id);

        System.out.println("User gelöscht");

        List<User> listUser = userRepo.findAll();
        model.addAttribute("listUser", listUser);


        return "/admin/userPanel";
    }




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

    /**
     * @author Andre
     */

    @GetMapping("/admin/timeRecording")
    public String timeRecording(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/admin/timeRecording";
    }

    @GetMapping("/admin/reportingView")
    public String reportingView(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/admin/reportingView";
    }

    @GetMapping("/admin/vacationManagement")
    public String vacationManagement(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/admin/vacationManagement";
    }

    @GetMapping("/admin/profileView")
    public String profileView(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/admin/profileView";
    }

}
