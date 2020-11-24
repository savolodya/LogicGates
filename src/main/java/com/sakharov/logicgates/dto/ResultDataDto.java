package com.sakharov.logicgates.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDataDto {
    List<Boolean> inputs;
    List<Boolean> outputs;
}
