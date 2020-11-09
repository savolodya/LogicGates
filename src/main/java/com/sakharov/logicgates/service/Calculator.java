package com.sakharov.logicgates.service;

import java.util.List;
import java.util.Map;

public interface Calculator {
    boolean calculate (List<String> rpn, Map<String, Boolean> parameters);
}
