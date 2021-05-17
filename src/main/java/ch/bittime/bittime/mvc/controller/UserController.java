package ch.bittime.bittime.mvc.controller;

import ch.bittime.bittime.login.TimeRecord;
import ch.bittime.bittime.login.User;
import ch.bittime.bittime.login.UserService;
import ch.bittime.bittime.login.repository.TimeRecordRepo;
import ch.bittime.bittime.login.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Andre
 */

@Controller
public class UserController {

        @Autowired
        private UserService userService;
        @Autowired
        private UserRepo userRepo;
    @Autowired
    private TimeRecordRepo timeRecordRepo;

        @GetMapping("/user/timeRecording")
        public String timeRecording(Model model){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByUserName(auth.getName());
            model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
            return "/user/timeRecording";
        }

    /**
     * @author Dominic
     */
    @PostMapping("/user/timeRecording")
    public String recordTime(@ModelAttribute TimeRecord timeRecord, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());

        timeRecord.setUser(user);

        // endtime>starttime?
        //endbreak>startbreak?
        //startbreak>starttime
        //endbreak<endtime

        if(timeRecord.getEndtime().getTime()>timeRecord.getStarttime().getTime() && timeRecord.getEndtime().getTime()>timeRecord.getStartbreak().getTime() && timeRecord.getStartbreak().getTime() > timeRecord.getStarttime().getTime() && timeRecord.getEndbreak().getTime() < timeRecord.getEndtime().getTime() ){
            timeRecordRepo.save(timeRecord);
        } else{
            //model.addAttribute errorMsg
            model.addAttribute( "recordTimeErrorMsg", "endtime greater starttime,endbreak greater startbreak, startbreak greater starttime, endbreak smaller endtime may not be applied correctly");
        }

        return timeRecording(model);
    }

        @GetMapping("/user/vacationView")
        public String vacationView(Model model){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByUserName(auth.getName());
            model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
            return "/user/vacationView";
    }

        @GetMapping("/user/reportingView")
        public String reportingView(Model model){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByUserName(auth.getName());
            //@Dominic timeRecords
            model.addAttribute("timeRecords", timeRecordRepo.findAllByUser(user));
            model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
            return "/user/reportingView";
        }

        @GetMapping("/user/profileView")
        public String profileView(Model model){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByUserName(auth.getName());
            model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
            return "/user/profileView";
        }

}