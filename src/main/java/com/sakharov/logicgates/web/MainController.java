package com.sakharov.logicgates.web;

import com.sakharov.logicgates.service.Calculator;
import com.sakharov.logicgates.service.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MainController {
    @Autowired
    private Calculator calc;
    @Autowired
    private Parser parser;

//    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
//    public boolean root() {
//        return true;
//    }

    @RequestMapping(value = "/result", method = RequestMethod.POST, produces = "application/json")
    public boolean result(@RequestParam Map<String,String> allParams) {
        Map <String, Boolean> parameters = new HashMap<>();
        String formula = allParams.get("formula");
        boolean result;

        parameters.put("ab", true);
        parameters.put("c", false);
        result = calc.calculate(parser.rpn(formula), parameters);

        return result;
    }
}
