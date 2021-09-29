package com.logicgates.service;

import java.util.List;
import java.util.Map;

/**
 * Interface for implementing service for calculation.
 */
public interface CalculatorService {
    boolean calculate(List<String> rpn, Map<String, Boolean> parameters);
}
