package com.koreait.autoanswer1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/usr/home/main")
    public String showMain(Model model) {

        return "usr/home/main";
    }
}