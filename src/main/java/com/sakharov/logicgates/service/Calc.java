package com.sakharov.logicgates.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Calc implements Calculator {
    @Override
    public boolean calculate(String rpn, Map<String, Boolean> parameters) {

        return false;
    }
}
