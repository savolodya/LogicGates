package com.sakharov.logicgates.controller;

import com.sakharov.logicgates.dto.ResultDataDto;
import com.sakharov.logicgates.service.CalculatorService;
import com.sakharov.logicgates.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
        reqParam.remove("formula");
        Map<String, Boolean> inputs = reqParam.entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> Boolean.parseBoolean(entry.getValue())
                        )
                );

        return calc.calculate(parser.rpn(formula), inputs);
    }

    @GetMapping("/truthTable")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ResultDataDto>> getTruthTable(@RequestParam Map<String, String> reqParam) {
        String formula = reqParam.get("formula");
        List<String> inputs = new ArrayList<>();
        List<ResultDataDto> truthTable = new ArrayList<>();

        reqParam.remove("formula");

        for (int i = 0; i < Math.pow(2, inputs.size()); i++) {
            ResultDataDto result = new ResultDataDto();

        }

        return new ResponseEntity<>(truthTable, HttpStatus.OK);
    }
}
