package com.sakharov.logicgates.web;

import com.sakharov.logicgates.service.Calculator;
import com.sakharov.logicgates.service.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private Calculator calc;

    @Autowired
    private Parser parser;

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
        Map <String, Boolean> parameters = new HashMap<>();
        String formula = allParams.get("formula");
        boolean result;

        parameters.put("a", true);
        parameters.put("b", false);
//        result = calc.calculate(formula.toLowerCase(), parameters);

        model.addAttribute("result", parser.rpn(formula));

        return "result";
    }
}
