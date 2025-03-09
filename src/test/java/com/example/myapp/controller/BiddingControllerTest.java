package com.example.myapp.controller;

import com.example.myapp.exception.BiasNotFoundException;
import com.example.myapp.service.BiddingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(BiddingController.class)
public class BiddingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BiddingService biddingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBid() throws Exception {
        Map<String, String> features = new HashMap<>();
        features.put("deviceExtBrowser", "Firefox");
        features.put("bannerExtSize", "300x250");
        features.put("deviceLanguage", "de");
        features.put("deviceExtType", "tablet");

        when(biddingService.calculateBid(features)).thenReturn(0.0016306374);

        String requestBody = "{ \"deviceExtBrowser\": \"Firefox\", \"bannerExtSize\": \"300x250\", \"deviceLanguage\": \"de\", \"deviceExtType\": \"tablet\" }";

        mockMvc.perform(post("/bid")
                .contentType("application/json")
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0016306374"))
                .andDo(print());
    }

    @Test
    public void testGetBidWithBiasNotFoundException() throws Exception {
        Map<String, String> features = new HashMap<>();
        features.put("deviceExtBrowser", "Firefox");
        features.put("bannerExtSize", "300x250");
        features.put("deviceLanguage", "de");
        features.put("deviceExtType", "tablet");

        when(biddingService.calculateBid(features)).thenThrow(new BiasNotFoundException("Bias not found in the model"));

        String requestBody = "{ \"deviceExtBrowser\": \"Firefox\", \"bannerExtSize\": \"300x250\", \"deviceLanguage\": \"de\", \"deviceExtType\": \"tablet\" }";

        mockMvc.perform(post("/bid")
                .contentType("application/json")
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bias not found in the model"))
                .andDo(print());
    }
}