package com.sakharov.logicgates.web;

import com.sakharov.logicgates.service.Calculator;
import com.sakharov.logicgates.service.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MainController {
    @Autowired
    private Calculator calc;
    @Autowired
    private Parser parser;

    @RequestMapping(value = "/result", method = RequestMethod.GET, produces = "application/json")
    public boolean result(@RequestParam Map<String, String> reqParam) {
        boolean result;
        String formula = reqParam.get("formula");

        reqParam.remove("formula");
        result = calc.calculate(parser.rpn(formula),
                reqParam.entrySet()
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        Map.Entry::getKey,
                                        entry -> Boolean.parseBoolean(entry.getValue())
                                )
                        )
        );

        return result;
    }
}
