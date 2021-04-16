package ch.bittime.bittime.mvc.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TestController {




    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView test (){
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("/test");
        modelAndView.addObject("messageController", "Wilkommen auf dem Testbenutzerprofil");
        return modelAndView;
    }

}
