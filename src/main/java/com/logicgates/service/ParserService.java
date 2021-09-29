package com.logicgates.service;

import java.util.List;

/**
 * Interface for implementing service for parsing formula.
 */
public interface ParserService {
    List<String> rpn(String formula);
}
