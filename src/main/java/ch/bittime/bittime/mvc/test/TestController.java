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
        modelAndView.setViewName("test");
        return modelAndView;
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public ModelAndView test2 (){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test2");
        return modelAndView;
    }
}
