package ch.bittime.bittime.webapp;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping
    String getPeople(Model model){
        model.addAttribute("something", "das kommt vom controller");
        return "people";
    }
}
