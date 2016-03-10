package pl.sagiton.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by szymon on 03.03.16.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(){
        return "home";
    }

    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String accessDeniedPage(){
        return "denied";
    }



}