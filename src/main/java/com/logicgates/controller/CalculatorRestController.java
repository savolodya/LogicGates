package com.logicgates.controller;

import com.logicgates.dto.ResultDataDto;
import com.logicgates.model.CalculatorModel;
import com.logicgates.service.CalculatorService;
import com.logicgates.service.GeneratorService;
import com.logicgates.service.ParserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * API Rest Controller which receives requests from user interface.
 */
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

    /**
     * Method for receiving POST request from user interface on address /result.
     * Produce result of a calculation.
     *
     * @param calculatorModel - formula and inputs.
     * @return result of calculation.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> result(
            @RequestBody CalculatorModel calculatorModel
    ) {
        Map<String, Boolean> inputs = IntStream.range(0, calculatorModel.getInputs().size())
                .collect(
                        HashMap::new,
                        (m, i) -> m.put(
                                calculatorModel.getInputs().get(i),
                                calculatorModel.getValues().get(i)
                        ),
                        Map::putAll
                );

        return new ResponseEntity<>(
                calc.calculate(parser.rpn(calculatorModel.getFormula()),
                        inputs),
                HttpStatus.OK
        );
    }

    /**
     * Method for receiving POST request from user interface on address /result/truthTable.
     * Produce truth table on produces on the basis of the formula and the number of inputs.
     *
     * @param calculatorModel - formula and inputs.
     * @return resulting truth table.
     */
    @PostMapping("/truthTable")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ResultDataDto>> getTruthTable(
            @RequestBody CalculatorModel calculatorModel
    ) {
        List<ResultDataDto> truthTable = new ArrayList<>();
        List<String> rpn = parser.rpn(calculatorModel.getFormula());
        List<List<Boolean>> inputsValue = generator.generate(calculatorModel.getInputs().size());

        for (List<Boolean> value : inputsValue) {
            Boolean result = calc.calculate(rpn,
                    IntStream.range(0, calculatorModel.getInputs().size())
                            .collect(
                                    HashMap::new,
                                    (m, j) -> m.put(
                                            calculatorModel.getInputs().get(j),
                                            value.get(j)
                                    ),
                                    Map::putAll
                            )
            );

            truthTable.add(new ResultDataDto(value, result));
        }

        return new ResponseEntity<>(truthTable, HttpStatus.OK);
    }
}
