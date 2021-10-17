package com.logicgates.service.impl;

import com.logicgates.service.CalculatorService;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.springframework.stereotype.Service;

/**
 * Implementation of service for calculation process.
 */
@Service
public class CalculatorServiceImpl implements CalculatorService {
    /**
     * Method for calculate result of a formula in RPN representation with given parameters.
     *
     * @param rpn RPN representation of formula.
     * @param parameters values of parameters in formula.
     * @return result of calculation.
     */
    @Override
    public boolean calculate(List<String> rpn, Map<String, Boolean> parameters) {
        Stack<Boolean> stack = new Stack<>();
        boolean result;

        for (String s : rpn) {
            if (s.matches("[&|]")) {
                boolean a = stack.pop();
                boolean b = stack.pop();

                switch (s) {
                    case "&" -> stack.push(a & b);
                    case "|" -> stack.push(a | b);
                    default -> {
                    }
                }
            } else if (s.matches("[!]")) {
                boolean a = stack.pop();
                stack.push(!a);
            } else {
                stack.push(parameters.get(s));
            }
        }

        result = stack.pop();

        return result;
    }
}
