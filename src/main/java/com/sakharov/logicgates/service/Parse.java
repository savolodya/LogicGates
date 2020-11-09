package com.sakharov.logicgates.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Service
public class Parse implements Parser {
    @Override
    public List<String> rpn(String formula) {
        // TODO: parse word params (ab&c)
        List<String> result = new ArrayList<>();
        Stack<String> opStack = new Stack<>();

        formula = prepare(formula);

        for (String token : formula.split("")) {
            if (isLetter(token))
                result.add(token);
            else if (token.equals("("))
                opStack.push(token);
            else if (token.equals(")")) {
                while (!opStack.peek().equals("("))
                    result.add(opStack.pop());
                opStack.pop();
            } else {
                while (!opStack.isEmpty() && getPriority(opStack.peek()) >= getPriority(token))
                    result.add(opStack.pop());
                opStack.push(token);
            }
        }

        while (!opStack.isEmpty())
            result.add(opStack.pop());

        return result;
    }

    private boolean isLetter(String token) {
        return Character.isLetter(token.charAt(0));
    }

    private int getPriority(String op) {
        switch (op) {
            case "(":
                return 0;
            case "|":
                return 1;
            case "&":
                return 2;
            case "!":
                return 3;
            default:
                return 4;
        }
    }

    private String prepare(String formula) {
        formula = formula.toLowerCase()
                .replaceAll("\\s", "")
                .replaceAll("[*^]", "&")
                .replaceAll("[+v]", "|")
                .replaceAll("[~-]", "!");

        return formula;
    }
}
