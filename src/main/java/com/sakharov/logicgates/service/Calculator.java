package com.sakharov.logicgates.service;

import java.util.Map;

public interface Calculator {
    boolean calculate (String rpn, Map<String, Boolean> parameters);
}
