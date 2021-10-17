package com.logicgates.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Model for receiving data from user interface.
 */
@Getter
@AllArgsConstructor
public class CalculatorModel {
    private String formula;
    private List<String> inputs;
    private List<Boolean> values;
}
