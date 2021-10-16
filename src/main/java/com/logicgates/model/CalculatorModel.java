package com.logicgates.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Model for receiving data from user interface.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculatorModel {
    private String formula;
    private List<String> inputs;
    private List<Boolean> values;
}
