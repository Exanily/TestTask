package com.example.testtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test2.properties")
public class TaskControllerProperties2Tests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCountCharsToStringReturn200Request() throws Exception {
        String strRequest = "ghfhhfgdfgffdvbnqwe";
        this.mockMvc.perform(post("/task?str=" + strRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("\"f\":5, \"g\":3, \"h\":3, \"d\":2, \"q\":1, \"b\":1, \"e\":1, \"v\":1, \"w\":1, \"n\":1"))
        ;
        String strNUllRequest = "";
        this.mockMvc.perform(post("/task?str=" + strNUllRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""))
        ;
    }


}
