package com.sakharov.logicgates.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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

    @PostMapping("/result")
    public String result(
            @RequestParam Map<String,String> allParams,
            Model model
    ) {



        model.addAttribute("result", allParams.get("formula"));

        return "result";
    }
}
