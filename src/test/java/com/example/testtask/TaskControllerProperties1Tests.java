package com.example.testtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@TestPropertySource("/application-test1.properties")
public class TaskControllerProperties1Tests {

    @Autowired
    private MockMvc mockMvc;

    @Value("${server.limit.request}")
    private String string;

    @Test
    public void testGetCountCharsToStringReturn200Request() throws Exception {
        String strRequest = "ghfhhfgdfgffdvbnqwe";
        this.mockMvc.perform(post("/task?str=" + strRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("\"f\":5, \"g\":3, \"h\":3, \"d\":2, \"q\":1, \"b\":1, \"e\":1"))
        ;
    }

    @Test
    public void testGetCountCharsToStringReturn400Request() throws Exception {
        String strRequest = "gdeghcfgdhdfghfdsfdsgfhjkhcvnawer";
        this.mockMvc.perform(post("/task?str=" + strRequest))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Error: The length of the string is more than " + string + " characters"))
        ;

    }
}
