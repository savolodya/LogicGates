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
    @Override
    public boolean calculate(List<String> rpn, Map<String, Boolean> parameters) {
        Stack<Boolean> stack = new Stack<>();
        boolean result;

        for (String s : rpn) {
            if (s.matches("[&|]")) {
                boolean a = stack.pop();
                boolean b = stack.pop();

                switch (s) {
                    case "&":
                        stack.push(a & b);
                        break;
                    case "|":
                        stack.push(a | b);
                        break;
                    default:
                        break;
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
