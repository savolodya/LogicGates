package com.logicgates.service.impl;

import com.logicgates.service.ParserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.springframework.stereotype.Service;

/**
 * Implementation of service for parsing formula.
 * Parsing method RPN (Reverse Polish Notation)
 */
@Service
public class ParserServiceImpl implements ParserService {
    /**
     * Method which converts human-readable formula to RPN form.
     *
     * @param formula calculation formula.
     * @return formula in the RPN form.
     */
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

                while (i + 1 < splitFormula.length
                        && isLetter(splitFormula[i + 1])) {
                    param.append(splitFormula[i + 1]);
                    i++;
                }
                result.add(param.toString());
            } else {
                switch (token) {
                    case "(" -> opStack.push(token);
                    case ")" -> {
                        while (!opStack.peek().equals("(")) {
                            result.add(opStack.pop());
                        }
                        opStack.pop();
                    }
                    default -> {
                        while (!opStack.isEmpty()
                                && getPriority(opStack.peek()) >= getPriority(token)) {
                            result.add(opStack.pop());
                        }
                        opStack.push(token);
                    }
                }
            }
        }

        while (!opStack.isEmpty()) {
            result.add(opStack.pop());
        }

        return result;
    }

    /**
     * Method for checking if character from a formula is a letter.
     *
     * @param token character from formula.
     * @return result of check if token is a letter.
     */
    private boolean isLetter(String token) {
        return Character.isLetter(token.charAt(0));
    }

    /**
     * Method for giving priority of operator in the formula.
     *
     * @param op operator.
     * @return priority of operator.
     */
    private int getPriority(String op) {
        return switch (op) {
            case "(" -> 0;
            case "&" -> 1;
            case "|" -> 2;
            case "!" -> 3;
            default -> 4;
        };
    }

    /**
     * Method which replace all possible representations of operators to one concrete operator.
     *
     * @param formula calculation formula.
     * @return prepared calculation formula.
     */
    private String prepare(String formula) {
        formula = formula
                .trim()
                .replaceAll("[*^]", "&")
                .replaceAll("[+]", "|")
                .replaceAll("[~-]", "!");

        return formula;
    }
}
