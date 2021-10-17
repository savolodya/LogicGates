package com.logicgates.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Model for transfer resulting data to user interface.
 */
@Getter
@AllArgsConstructor
public class ResultDataDto {
    private List<Boolean> inputs;
    private Boolean output;
}
