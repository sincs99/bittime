package ch.bittime.bittime.mvc.controller;

import ch.bittime.bittime.login.*;
import ch.bittime.bittime.login.repository.SickdayRepo;
import ch.bittime.bittime.login.repository.TimeRecordRepo;
import ch.bittime.bittime.login.repository.UserRepo;
import ch.bittime.bittime.login.repository.VacationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

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
    @Autowired
    private VacationRepo vacationRepo;
    @Autowired
    private SickdayRepo sickdayRepo;

    @GetMapping("/user/timeRecording")
    public String timeRecording(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/user/timeRecording";
    }

    /**
     * @author Dominic
     */
    @PostMapping("/user/timeRecording")
    public String recordTime(@ModelAttribute TimeRecord timeRecord, Model model) {
        User user = assignUser(model);
        timeRecord.setUser(user);

        // endtime > starttime?
        // endbreak >= startbreak?
        // startbreak >= starttime?
        // endbreak <= endtime?

        if (timeRecord.getEndtime().getTime() > timeRecord.getStarttime().getTime() &&
                timeRecord.getEndtime().getTime() >= timeRecord.getStartbreak().getTime() &&
                timeRecord.getStartbreak().getTime() >= timeRecord.getStarttime().getTime() &&
                timeRecord.getEndbreak().getTime() <= timeRecord.getEndtime().getTime()) {
            timeRecordRepo.save(timeRecord);
        } else {
            model.addAttribute("recordTimeErrorMsg", "Storing the time record was not successful. Please enter a start time that takes place before the end time and at least one break for your health within that interval.");
        }

        return timeRecording(model);
    }

    /**
     * @author Andre
     */
    @GetMapping("/user/vacationView")
    public String vacationView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/user/vacationView";
    }

    /**
     * @author Dominic
     */
    @PostMapping("/user/vacationView")
    public String recordVacation(@ModelAttribute Vacation vacation, Model model) {
        User user = assignUser(model);
        vacation.setUser(user);

        // enddate > startdate?
        if (vacation.getEndDate().getTime() >= vacation.getStartDate().getTime()) {
            vacationRepo.save(vacation);
        } else {
            model.addAttribute("vacationErrorMsg", "Vacation request was not successful. Please enter a vacation end date that take place after the start date.");
        }
        return vacationView(model);
    }

    /**
     * @author Andre
     */
    @GetMapping("/user/reportingView")
    public String reportingView(Model model) {
        User user = assignUser(model);
        // @Dominic timeRecords
        model.addAttribute("timeRecords", timeRecordRepo.findAllByUser(user));
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/user/reportingView";
    }

    @GetMapping("/user/profileView")
    public String profileView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/user/profileView";
    }

    /**
     * @author Dominic, Andre
     */

    @PostMapping("/user/profileView")
    public String updateProfileView(Model model, @ModelAttribute User updatedUser, String currentPw, String newPw1, String newPw2) {

        User user = assignUser(model);

        if(!userService.matchesPassword(currentPw, user)) {
            model.addAttribute("errorMsg", "The entered password is incorrect.");
        } else if(!Objects.equals(newPw1, newPw2)) {
            model.addAttribute("errorMsg", "New password does not match.");
        } else if(updatedUser.getName() == null || updatedUser.getName().equals("") ||
                updatedUser.getLastName() == null || updatedUser.getLastName().equals("") ||
                //updatedUser.getUserName() == null || updatedUser.getUserName().equals("") ||
                updatedUser.getEmail() == null || updatedUser.getEmail().equals("")) {
            model.addAttribute("errorMsg", "Name, last name and email can't be empty.");
        } else {
            // Username cannot be changed because it is stored in security context.
            // Securitz context cannot be updated with new username. Would require logout.
            //user.setUserName(updatedUser.getUserName());
            user.setEmail(updatedUser.getEmail());
            user.setName(updatedUser.getName());
            user.setLastName(updatedUser.getLastName());
            user.setStreet(updatedUser.getStreet());
            user.setCity(updatedUser.getCity());
            user.setState(updatedUser.getState());
            if(newPw1 != null && newPw1.length() > 0) {
                user.setPassword(userService.encodePassword(newPw1));
            }
            userRepo.save(user);
            return profileView(model);
        }

        // Only get here in error cases
        // Do not delegate to profileView method because we show back changes
        // to user along with errors
        model.addAttribute("user", updatedUser);
        return "/user/profileView";
    }

    @GetMapping("/user/sickRecording")
    public String sickRecording(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/user/sickRecording";
    }

    /**
     * @author Dominic
     */
    @PostMapping("/user/sickRecording")
    public String recordSickdays(@ModelAttribute Sickday sickday, Model model) {
        User user = assignUser(model);
        sickday.setUser(user);

        // enddate > startdate?
        if (sickday.getEndDate().getTime() >= sickday.getStartDate().getTime()) {
            sickdayRepo.save(sickday);
        } else {
            model.addAttribute("sickdayErrorMsg", "Sickday recording was not successful. Please enter a sickday's end date/s that take place after the start date/s.");
        }
        return sickRecording(model);
    }

    /**
     * @author Dominic
     */
    private User assignUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return user;
    }


}