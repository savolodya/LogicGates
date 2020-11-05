package com.sakharov.logicgates.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("title", "Playground");

        return "index";
    }

    @GetMapping("/help")
    public String help(Model model) {
        model.addAttribute("title", "Help");

        return "help";
    }
}
