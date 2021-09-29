package com.logicgates.service;

import java.util.List;

/**
 * Interface for implementing service for generating list of inputs for truth table.
 */
public interface GeneratorService {
    List<List<Boolean>> generate(int length);
}
