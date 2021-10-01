package com.logicgates;

import com.logicgates.controller.CalculatorRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ControllerTest {
    @Autowired
    private CalculatorRestController calculatorRestController;

    @Test
    public void contextLoads() {
        assertThat(calculatorRestController).isNotNull();
    }
}
