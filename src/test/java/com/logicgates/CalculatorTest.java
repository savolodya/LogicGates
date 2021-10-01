package com.logicgates;

import com.logicgates.service.CalculatorService;
import com.logicgates.service.GeneratorService;
import com.logicgates.service.ParserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CalculatorTest {
    @Autowired
    ParserService parser;

    @Autowired
    CalculatorService calc;

    @Autowired
    GeneratorService generator;

    @Test
    public void testFormulaParser() {
        String formula = "!((a+b|c)&d*e)";
        List<String> result = Arrays.stream("ab|c|d&e&!".split("")).toList();

        assertThat(parser.rpn(formula)).isEqualTo(result);
    }

    @Test
    public void testCalculator() {
        List<String> rpn = Arrays.stream("ab|c|d&e&!".split("")).toList();
        Map<String, Boolean> parameters = new HashMap<>();
        parameters.put("a", true);
        parameters.put("b", true);
        parameters.put("c", true);
        parameters.put("d", true);
        parameters.put("e", true);

        assertThat(calc.calculate(rpn, parameters)).isEqualTo(false);
    }

    @Test
    public void testGenerator() {
        int numParameters = 2;
        List<List<Boolean>> result = new ArrayList<>();
        result.add(Arrays.stream(new Boolean[] {false, false}).toList());
        result.add(Arrays.stream(new Boolean[] {false, true}).toList());
        result.add(Arrays.stream(new Boolean[] {true, false}).toList());
        result.add(Arrays.stream(new Boolean[] {true, true}).toList());

        assertThat(generator.generate(numParameters)).isEqualTo(result);
    }
}
