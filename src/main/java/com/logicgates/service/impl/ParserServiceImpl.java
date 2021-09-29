package com.logicgates.service.impl;

import com.logicgates.service.ParserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.springframework.stereotype.Service;

/**
 * Implementation of service for parsing formula.
 * Paring method RPN (Reverse Polish Notation)
 */
@Service
public class ParserServiceImpl implements ParserService {
    @Override
    public List<String> rpn(String formula) {
        List<String> result = new ArrayList<>();
        Stack<String> opStack = new Stack<>();

        formula = prepare(formula);

        String[] splitFormula = formula.split("");

        for (int i = 0; i < splitFormula.length; i++) {
            String token = splitFormula[i];
            if (isLetter(token)) {
                StringBuilder param = new StringBuilder(token);

                while (i + 1 < splitFormula.length && isLetter(splitFormula[i + 1])) {
                    param.append(splitFormula[i + 1]);
                    i++;
                }
                result.add(param.toString());
            } else {
                switch (token) {
                    case "(":
                        opStack.push(token);
                        break;
                    case ")":
                        while (!opStack.peek().equals("(")) {
                            result.add(opStack.pop());
                        }
                        opStack.pop();
                        break;
                    default:
                        while (
                                !opStack.isEmpty()
                                        && getPriority(opStack.peek()) >= getPriority(token)
                        ) {
                            result.add(opStack.pop());
                        }
                        opStack.push(token);
                        break;
                }
            }
        }

        while (!opStack.isEmpty()) {
            result.add(opStack.pop());
        }

        return result;
    }

    private boolean isLetter(String token) {
        return Character.isLetter(token.charAt(0));
    }

    private int getPriority(String op) {
        switch (op) {
            case "(":
                return 0;
            case "&":
                return 1;
            case "|":
                return 2;
            case "!":
                return 3;
            default:
                return 4;
        }
    }

    private String prepare(String formula) {
        formula = formula
                .trim()
                .replaceAll("[*^]", "&")
                .replaceAll("[+]", "|")
                .replaceAll("[~-]", "!");

        return formula;
    }
}
