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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class HttpRequestsTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnTrue() throws Exception {
        CalculatorModel calculatorModel = new CalculatorModel("a|b", new ArrayList<>(){{add("a"); add("b");}}, new ArrayList<>(){{add(true); add(true);}});

        this.mockMvc.perform(post("/result")
                        .content(asJsonString(calculatorModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
