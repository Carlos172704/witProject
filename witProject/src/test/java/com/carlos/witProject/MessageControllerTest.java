package com.carlos.witProject;


import com.carlos.witProject.controller.MessageController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Producer producer;

    @InjectMocks
    private MessageController messageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
    }

    @Test
    void testPublishSum() throws Exception {
        mockMvc.perform(get("/api/get/sum")
                        .param("a", "10")
                        .param("b", "5"))
                .andExpect(status().isOk());

        verify(producer, times(1)).sendMessage("15.00");
    }

    @Test
    void testPublishSub() throws Exception {
        mockMvc.perform(get("/api/get/sub")
                        .param("a", "10")
                        .param("b", "5"))
                .andExpect(status().isOk());

        verify(producer, times(1)).sendMessage("5.00");
    }

    @Test
    void testPublishMult() throws Exception {
        mockMvc.perform(get("/api/get/mult")
                        .param("a", "10")
                        .param("b", "5"))
                .andExpect(status().isOk());

        verify(producer, times(1)).sendMessage("50.00");
    }

    @Test
    void testPublishDiv() throws Exception {
        mockMvc.perform(get("/api/get/div")
                        .param("a", "10")
                        .param("b", "5"))
                .andExpect(status().isOk());

        verify(producer, times(1)).sendMessage("2.00");
    }

    @Test
    void testPublishSumWithInvalidNumbers() throws Exception {
        mockMvc.perform(get("/api/get/sum")
                        .param("a", "invalid")
                        .param("b", "5"))
                .andExpect(status().is(400));

        verify(producer, times(1)).sendMessage("The values should be numbers only!");
    }

    @Test
    void testPublishSubWithInvalidNumbers() throws Exception {
        mockMvc.perform(get("/api/get/sub")
                        .param("a", "10")
                        .param("b", "invalid"))
                .andExpect(status().is(400));

        verify(producer, times(1)).sendMessage("The values should be numbers only!");
    }
}
