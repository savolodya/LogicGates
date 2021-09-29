package com.logicgates.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for transfer resulting data to user interface.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDataDto {
    List<Boolean> inputs;
    Boolean output;
}
