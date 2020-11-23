package com.sakharov.logicgates.controller;

import com.sakharov.logicgates.service.CalculatorService;
import com.sakharov.logicgates.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/result")
public class CalculatorRestController {
    @Autowired
    private CalculatorService calc;
    @Autowired
    private ParserService parser;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean result(@RequestParam Map<String, String> reqParam) {
        String formula = reqParam.get("formula");
        Map<String, Boolean> inputs = reqParam.entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> Boolean.parseBoolean(entry.getValue())
                        )
                );

        reqParam.remove("formula");
        return calc.calculate(parser.rpn(formula), inputs);
    }
}
