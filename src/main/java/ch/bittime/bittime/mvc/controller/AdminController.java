package ch.bittime.bittime.mvc.controller;

/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
#
Pascal
#
 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

import ch.bittime.bittime.login.User;
import ch.bittime.bittime.login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ch.bittime.bittime.login.repository.UserRepo;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/admin/userPanel")
    public String listUser(Model model){
        List<User> listUser = userRepo.findAll();
        model.addAttribute("listUser", listUser);
        System.out.println(listUser);
        return "/admin/userPanel";




    }




}
