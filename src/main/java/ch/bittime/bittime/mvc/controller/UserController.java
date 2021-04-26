package ch.bittime.bittime.mvc.controller;

import ch.bittime.bittime.login.UserService;
import ch.bittime.bittime.login.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Andre
 */

public class UserController {

        @Autowired
        private UserService userService;
        @Autowired
        private UserRepo userRepo;

    ////    @RequestMapping(value = "admin/deleteUser", method = RequestMethod.DELETE)
    ////    public String deleteUser(Model model){
    ////        List<User> listUser =userRepo.finb
    ////    }
    //
    //
    //    @GetMapping("/admin/userPanel")
    //    public String listUser(Model model){
    //        List<User> listUser = userRepo.findAll();
    //        model.addAttribute("listUser", listUser);
    //        System.out.println(listUser);
    //
    //        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //        User user = userService.findUserByUserName(auth.getName());
    //        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
    //
    //        return "/admin/userPanel";
    //
    //    }
    //
    //    @GetMapping("/admin/timeRecording")
    //    public String timeRecording(){
    //        return "/admin/timeRecording";
    //    }
    //
    //    @GetMapping("/admin/reportingView")
    //    public String reportingView(){
    //        return "/admin/reportingView";
    //    }
    //
    //    @GetMapping("/admin/vacationManagement")
    //    public String vacationManagement(){
    //        return "/admin/vacationManagement";
    //    }
    //
    //    @GetMapping("/admin/profileView")
    //    public String profileView(){
    //        return "/admin/profileView";
    //    }

}
