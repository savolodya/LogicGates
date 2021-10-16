package com.logicgates.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Model for transfer resulting data to user interface.
 */
@Data
@AllArgsConstructor
public class ResultDataDto {
    private List<Boolean> inputs;
    private Boolean output;
}
