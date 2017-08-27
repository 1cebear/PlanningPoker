package ru.planningpoker.controller;

/**
 * Created by Icebear on 06.06.2017.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class RootController {

    @GetMapping(value = "/")
    public String root() {
        return "index";
    }

    @GetMapping(value = "/index")
    public String login() {
        return "index";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        return "main";
    }

}

