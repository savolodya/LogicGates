package com.logicgates.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculatorModel {
    String formula;
    List<String> inputs;
    List<Boolean> values;
}
