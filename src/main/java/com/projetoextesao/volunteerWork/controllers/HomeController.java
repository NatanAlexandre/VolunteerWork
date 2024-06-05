package com.projetoextesao.volunteerWork.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "index";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

    @GetMapping("/project")
    public String project(){
        return "projects";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
