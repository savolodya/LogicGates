package com.sakharov.logicgates.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Stack;

@Service
public class Parse implements Parser {
    @Override
    public String rpn(String formula) {
        StringBuilder result = new StringBuilder();
        ArrayList<Character> resultList = new ArrayList<>();
        Stack<Character> opStack = new Stack<>();

        formula = prepare(formula);

        for (char token : formula.toCharArray()) {
            if (isLetter(token))
                resultList.add(token);
            else if (token == '(')
                opStack.push(token);
            else if (token == ')') {
                while (opStack.peek() != '(')
                    resultList.add(opStack.pop());
                opStack.pop();
            } else {
                while (!opStack.isEmpty() && getPriority(opStack.peek()) >= getPriority(token))
                    resultList.add(opStack.pop());
                opStack.push(token);
            }
        }

        while (!opStack.isEmpty())
            resultList.add(opStack.pop());

        for (char c: resultList)
            result.append(c);

        return result.reverse().toString();
    }

    private boolean isLetter(char token) {
        return Character.isLetter(token);
    }

    private int getPriority(char op) {
        if (op == '(') {
            return 0;
        } else if (op == '|') {
            return 1;
        } else if (op == '&') {
            return 2;
        } else if (op == '!') {
            return 3;
        } else
            return 4;
    }

    private String prepare(String formula) {
        formula = formula.toLowerCase()
                .replaceAll("[*^]", "&")
                .replaceAll("[+v]", "|")
                .replaceAll("[~-]", "!");

        return formula;
    }
}
