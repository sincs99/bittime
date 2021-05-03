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
import org.springframework.web.servlet.ModelAndView;

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


    @RequestMapping(value = "/admin/deleteUser/{id}")
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


        return "redirect:/admin/userPanel";
    }

    @RequestMapping("/admin/activateUser/{id}")
    public String activateUser(@PathVariable(name = "id") int id, Model model) {

        System.out.println("Request ok");
        Optional<User> i = userService.findUserById(id);
        System.out.println(i);
        String roles;


        roles = String.valueOf(i.get().getRoles());
        System.out.println(roles);

        if (roles.contains("USER")) {
            System.out.println("ist User");
            userService.activateUser(id);
            model.addAttribute("activateUser", "User successful activated!");
            System.out.println("User aktiviert");
        } else {
            System.out.println("ist Admin");
            model.addAttribute("activateAdmin", "You cannot activate an Admin");
            System.out.println("Admin kann nicht aktiviert werden");
        }


        System.out.println(i);


        List<User> listUser = userRepo.findAll();
        model.addAttribute("listUser", listUser);


        return "redirect:/admin/userPanel";
    }

    @RequestMapping("/admin/deactivateUser/{id}")
    public String deactivateUser(@PathVariable(name = "id") int id, Model model) {



        System.out.println("Request ok");
        Optional<User> i = userService.findUserById(id);
        System.out.println(i);
        String roles;



        roles = String.valueOf(i.get().getRoles());
        System.out.println(roles);

        if (roles.contains("USER")) {
            System.out.println("ist User");
            userService.deactivateUser(id);
            model.addAttribute("deactivateUser", "User successful deactivated!");
            System.out.println("User deaktiviert");
        } else {
            System.out.println("ist Admin");
            model.addAttribute("deactivateAdmin", "You cannot deactivate an Admin");
            System.out.println("Admin kann nicht deaktiviert werden");
        }


        System.out.println(i);


        List<User> listUser = userRepo.findAll();
        model.addAttribute("listUser", listUser);



        return "redirect:/admin/userPanel";

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
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
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

    @GetMapping("/admin/profileView")
    public String profileView(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/admin/profileView";
    }

}
