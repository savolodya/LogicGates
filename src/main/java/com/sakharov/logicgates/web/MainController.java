package com.sakharov.logicgates.web;

import com.sakharov.logicgates.service.Calculator;
import com.sakharov.logicgates.service.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MainController {
    @Autowired
    private Calculator calc;

    @Autowired
    private Parser parser;

//    @GetMapping("/")
//    public String root(Model model) {
//        model.addAttribute("title", "Playground");
//
//        return "index";
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public List<String> root() {
        List<String> list = new ArrayList<>();

        list.add("a");
        list.add("b");

        return list;
    }

//    @GetMapping("/help")
//    public String help(Model model) {
//        model.addAttribute("title", "Help");
//
//        return "help";
//    }

//    @PostMapping("/result")
//    public String result(
//            @RequestParam Map<String,String> allParams,
//            Model model
//    ) {
//        Map <String, Boolean> parameters = new HashMap<>();
//        String formula = allParams.get("formula");
//        boolean result;
//
//        parameters.put("ab", true);
//        parameters.put("c", false);
//        result = calc.calculate(parser.rpn(formula), parameters);
//
//        model.addAttribute("formula", formula);
//        model.addAttribute("result", result);
//
//        return "result";
//    }
}
