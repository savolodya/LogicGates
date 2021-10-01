package com.logicgates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicgates.model.CalculatorModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HttpRequestsTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void requestOnResultShouldBeOK() throws Exception {
        List<String> inputs = new ArrayList<>();
        List<Boolean> values = new ArrayList<>();
        inputs.add("a");
        values.add(true);

        CalculatorModel calculatorModel = new CalculatorModel("a", inputs, values);

        this.mockMvc.perform(post("/result")
                        .content(asJsonString(calculatorModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void requestOnTruthTableShouldBeOK() throws Exception {
        List<String> inputs = new ArrayList<>();
        inputs.add("a");

        CalculatorModel calculatorModel = new CalculatorModel("a", inputs, null);

        this.mockMvc.perform(post("/result/truthTable")
                        .content(asJsonString(calculatorModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
