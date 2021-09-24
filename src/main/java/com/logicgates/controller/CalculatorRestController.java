package com.logicgates.controller;

import com.logicgates.dto.ResultDataDto;
import com.logicgates.model.CalculatorModel;
import com.logicgates.service.CalculatorService;
import com.logicgates.service.GeneratorService;
import com.logicgates.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/result")
public class CalculatorRestController {
    @Autowired
    private CalculatorService calc;
    @Autowired
    private ParserService parser;
    @Autowired
    private GeneratorService generator;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean result(
            @RequestParam Map<String, String> reqParam
    ) {
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

    @PostMapping("/truthTable")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ResultDataDto>> getTruthTable(
            @RequestBody CalculatorModel calculatorModel
    ) {
        System.out.println(calculatorModel.getFormula() + " " + calculatorModel.getInputs());
        List<ResultDataDto> truthTable = new ArrayList<>();
        List<String> rpn = parser.rpn(calculatorModel.getFormula());
        List<List<Boolean>> inputsValue = generator.generate(calculatorModel.getInputs().size());

        for (List<Boolean> value : inputsValue) {
            Boolean result = calc.calculate(rpn,
                    IntStream.range(0, calculatorModel.getInputs().size())
                            .collect(
                                    HashMap::new,
                                    (m, j) -> m.put(calculatorModel.getInputs().get(j), value.get(j)),
                                    Map::putAll
                            )
            );

            truthTable.add(new ResultDataDto(value, result));
        }

        return new ResponseEntity<>(truthTable, HttpStatus.OK);
    }
}
