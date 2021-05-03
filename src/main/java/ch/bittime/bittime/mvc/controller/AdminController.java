package ch.bittime.bittime.mvc.controller;

/**
 * @author Pascal
 */

import ch.bittime.bittime.login.User;
import ch.bittime.bittime.login.UserService;
import ch.bittime.bittime.login.Vacation;
import ch.bittime.bittime.login.repository.VacationRepo;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
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
    //vacationRep
    @Autowired
    private VacationRepo vacationRepo;


    @RequestMapping("/admin/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") int id, Model model) {

        System.out.println("Request ok");
        Optional<User> i = userService.findUserById(id);
        System.out.println(i);
        String roles;


        roles = String.valueOf(i.get().getRoles());
        System.out.println(roles);

        if (roles.contains("USER")) {
            System.out.println("ist User");
            userService.deleteUser(id);
            model.addAttribute("deleteSuc", "User successful deleted!");
            System.out.println("User gelöscht");
        } else {
            System.out.println("ist Admin");
            model.addAttribute("deleteError", "You cannot delete an Admin");
            System.out.println("Admin kann nicht gelöscht werden");
        }


        System.out.println(i);


        List<User> listUser = userRepo.findAll();
        model.addAttribute("listUser", listUser);


        return "/admin/userPanel";
    }


    @GetMapping("/admin/userPanel")
    public String listUser(Model model) {
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
    public String timeRecording(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/admin/timeRecording";
    }

    @GetMapping("/admin/reportingView")
    public String reportingView(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getName());
        return "/admin/reportingView";
    }

    @GetMapping("/admin/vacationManagement")
    public String vacationManagement(Model model) {

/**
 * @author Dominic
 */
        //list, vacationRepo
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());

        List<Vacation> listVacation = vacationRepo.findAll();
        Vacation v = new Vacation();
        v.setUser(user);
        listVacation.add(v);
        model.addAttribute("listVacation", listVacation);
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/admin/vacationManagement";
    }

}
