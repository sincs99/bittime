package ch.bittime.bittime.mvc.controller;

/**
 * @author Pascal, Dominic, Andre
 */

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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Pascal
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private VacationRepo vacationRepo;
    @Autowired
    private TimeRecordRepo timeRecordRepo;
    @Autowired
    private SickdayRepo sickdayRepo;

    @RequestMapping("/deleteUser/{id}")
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

        /* Dominic @ Pascal test if user has db table entries that are displayed in application...
        -> sonst error bei Report und vacation mgt im Falle von null Einträgen...
         */


        System.out.println(i);


        List<User> listUser = userRepo.findAll();
        model.addAttribute("listUser", listUser);


        return "redirect:/admin/userPanel";
    }

    @RequestMapping("/activateUser/{id}")
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

    @RequestMapping("/deactivateUser/{id}")
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

    @GetMapping("/userPanel")
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
    @GetMapping("/timeRecording")
    public String timeRecording(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/admin/timeRecording";
    }

    /**
     * @author Dominic
     */
    @PostMapping("/timeRecording")
    public String recordTime(@ModelAttribute TimeRecord timeRecord, Model model) {
        User user = assignUser(model);
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //User user = userService.findUserByUserName(auth.getName());

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
            //model.addAttribute errorMsg
            model.addAttribute("recordTimeErrorMsg", "Storing the time record was not successful. Please enter a start time that takes place before the end time and at least one break for your health within that interval.");
        }

        return timeRecording(model);
    }

    /**
     * @author Andre
     */
    @GetMapping("/vacationRecording")
    public String vacationRecording(Model model) {
        assignUser(model);
        return "/admin/vacationRecording";
    }

    /**
     * @author Dominic
     */
    @PostMapping("/vacationRecording")
    public String recordVacation(@ModelAttribute Vacation vacation, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        vacation.setUser(user);

        // enddate >= startdate?
        if (vacation.getEndDate().getTime() >= vacation.getStartDate().getTime()) {
            vacationRepo.save(vacation);
        } else {

            //model.addAttribute errorMsg
            model.addAttribute("vacationErrorMsg", "Vacation request was not successful. Please enter a vacation end date that take place after the start date.");
        }
        return vacationRecording(model);
    }

    /**
     * @author Andre
     */
    @GetMapping("/sickRecording")
    public String sickRecording(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/admin/sickRecording";
    }

    /**
     * @author Dominic
     */
    @PostMapping("/sickRecording")
    public String recordSickdays(@ModelAttribute Sickday sickday, Model model) {
        User user = assignUser(model);
        sickday.setUser(user);

        //enddate>startdate?
        if (sickday.getEndDate().getTime() >= sickday.getStartDate().getTime()) {
            sickdayRepo.save(sickday);
        } else {

            //model.addAttribute errorMsg
            model.addAttribute("sickdayErrorMsg", "Sickday recording was not successful. Please enter a sickday's end date/s that take place after the start date/s.");
        }
        return sickRecording(model);
    }

    /**
     * @author Dominic
     */
    @GetMapping("/reportingView")
    public String reportingView(@RequestParam(defaultValue = "-1") int userId, /*@RequestParam(defaultValue = "") String timeFilter,*/ Model model) {
        User user = assignUser(model);
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //User user = userService.findUserByUserName(auth.getName());
        // List<TimeRecord> timeRecordList;

        //@Dominic timeRecords
        if (userId == -1) model.addAttribute("timeRecords", timeRecordRepo.findAll());
        else {
            userRepo.findById(userId).ifPresentOrElse(filteredUser ->
                            model.addAttribute("timeRecords", timeRecordRepo.findAllByUser(filteredUser)),
                    () -> model.addAttribute("timeRecords", timeRecordRepo.findAll())
            );
        }
        // model.addAttribute("timeRecords", timeRecordList.stream().filter(timeFilter).collect(Collectors.toList()));
        model.addAttribute("userList", userRepo.findAll());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/admin/reportingView";
    }

    /**
     * @author Andre
     */
    @GetMapping("/profileView")
    public String profileView(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/admin/profileView";
    }

    //    todo: edit profile function
//    @RequestMapping("/editProfile/{id}")
//    public String changeProfile(@PathVariable(name = "id") int id, Model model) {
//        System.out.println("Method got called");
//        Optional<User> i = userService.findUserById(id);
//        System.out.println("User gefunden:"+i);
//        userService.changeName(id);
//
//        return "redirect:/admin/profileView";
//    }


    /**
     * @author Dominic
     */
    @GetMapping("/vacationManagement")
    public String vacationManagement(Model model) {
        //list, vacationRepo
        User user = assignUser(model);
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //User user = userService.findUserByUserName(auth.getName());

        List<Vacation> listVacation = vacationRepo.findAll();
//        Vacation v = new Vacation();
//        v.setUser(user);
        // listVacation.add(v);
        model.addAttribute("listVacation", listVacation);
        model.addAttribute("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        return "/admin/vacationManagement";
    }

    /**
     * @author Dominic
     */
    @PostMapping("/vacationManagement")
    public String acceptOrDeclineVacation(@RequestParam int vacation_id, @RequestParam String result, Model model) {
        vacationRepo.findById(vacation_id).ifPresent(vacation -> {
                    if (vacation.getAcceptState() == 0) {
                        vacation.setAcceptState("accept".equals(result) ? 1 : -1);
                        vacationRepo.save(vacation);
                    }
                }
        );
        return vacationManagement(model);
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
