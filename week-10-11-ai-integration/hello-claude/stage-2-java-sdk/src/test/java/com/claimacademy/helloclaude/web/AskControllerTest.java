package com.claimacademy.helloclaude.web;

import com.claimacademy.helloclaude.ai.AiAnswer;
import com.claimacademy.helloclaude.ai.ClaudeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests the controller WITHOUT calling Claude: ClaudeService is replaced by a Mockito mock.
 * No API key, no network, no cost. Every AI feature should be testable like this.
 */
@WebMvcTest(AskController.class)
class AskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClaudeService claude;

    @Test
    void returnsClaudesAnswer() throws Exception {
        when(claude.ask(anyString(), eq("What is a JVM?")))
                .thenReturn(new AiAnswer("It runs Java bytecode.", "claude-haiku-5-5", "end_turn", 30, 8));

        mockMvc.perform(post("/api/ai/ask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"question\":\"What is a JVM?\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("It runs Java bytecode."))
                .andExpect(jsonPath("$.outputTokens").value(8));
    }

    @Test
    void blankQuestionIs400() throws Exception {
        mockMvc.perform(post("/api/ai/ask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"question\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("question is required"));
    }
}
