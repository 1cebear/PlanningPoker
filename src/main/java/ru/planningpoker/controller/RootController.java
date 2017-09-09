package ru.planningpoker.controller;

/**
 * Created by Icebear on 06.06.2017.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import ru.planningpoker.controller.user.AbstractUserController;
import ru.planningpoker.model.User;

import javax.validation.Valid;


@Controller
public class RootController extends AbstractUserController{

    @GetMapping(value = "/")
    public String root() {
        return "redirect:main";
    }

    @GetMapping(value = "/index")
    public String login() {
        return "index";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        return "main";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid User user, BindingResult result, SessionStatus status, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "register";
        } else {
            super.create(user);
            status.setComplete();
            return "redirect:index";
        }
    }

}

