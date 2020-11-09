package com.sakharov.logicgates.service;

import java.util.List;

public interface Parser {
    List<String> rpn(String formula);
}
