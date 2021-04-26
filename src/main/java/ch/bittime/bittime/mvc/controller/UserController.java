package ch.bittime.bittime.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Andre
 */

@Controller
public class UserController {

        @GetMapping("/user/timeRecording")
        public String timeRecording(){
            return "/user/timeRecording";
        }

        @GetMapping("/user/vacationView")
        public String vacationView(){ return "/user/vacationView";
    }

        @GetMapping("/user/reportingView")
        public String reportingView(){
            return "/user/reportingView";
        }

        @GetMapping("/user/profileView")
        public String profileView(){
            return "/user/profileView";
        }

}