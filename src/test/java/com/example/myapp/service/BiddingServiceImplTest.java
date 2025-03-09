package com.example.myapp.service;

import com.example.myapp.exception.BiasNotFoundException;
import com.example.myapp.repository.BiddingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class BiddingServiceImplTest {

    @Mock
    private BiddingRepository biddingRepository;

    @InjectMocks
    private BiddingServiceImpl biddingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateBid() {
        when(biddingRepository.getBias()).thenReturn(-6.21176449);
        when(biddingRepository.getCoefficient("deviceExtBrowser=Firefox")).thenReturn(-0.1131013246);
        when(biddingRepository.getCoefficient("bannerExtSize=300x250")).thenReturn(-0.6282185905);
        when(biddingRepository.getCoefficient("deviceLanguage=de")).thenReturn(-0.1935418172);
        when(biddingRepository.getCoefficient("deviceExtType=tablet")).thenReturn(0.7294739471);

        Map<String, String> features = new HashMap<>();
        features.put("deviceExtBrowser", "Firefox");
        features.put("bannerExtSize", "300x250");
        features.put("deviceLanguage", "de");
        features.put("deviceExtType", "tablet");

        double result = biddingService.calculateBid(features);
        double expected = 1 / (1 + Math.exp(-(-6.21176449 - 0.1131013246 - 0.6282185905 - 0.1935418172 + 0.7294739471)));
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testCalculateBidWithMissingCoefficients() {
        when(biddingRepository.getBias()).thenReturn(-6.21176449);
        when(biddingRepository.getCoefficient("deviceExtBrowser=Firefox")).thenReturn(null);
        when(biddingRepository.getCoefficient("bannerExtSize=300x250")).thenReturn(-0.6282185905);
        when(biddingRepository.getCoefficient("deviceLanguage=de")).thenReturn(null);
        when(biddingRepository.getCoefficient("deviceExtType=tablet")).thenReturn(0.7294739471);

        Map<String, String> features = new HashMap<>();
        features.put("deviceExtBrowser", "Firefox");
        features.put("bannerExtSize", "300x250");
        features.put("deviceLanguage", "de");
        features.put("deviceExtType", "tablet");

        double result = biddingService.calculateBid(features);
        double expected = 1 / (1 + Math.exp(-(-6.21176449 - 0.6282185905 + 0.7294739471)));
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testCalculateBidWithEmptyFeatures() {
        when(biddingRepository.getBias()).thenReturn(-6.21176449);

        Map<String, String> features = new HashMap<>();

        double result = biddingService.calculateBid(features);
        double expected = 1 / (1 + Math.exp(-(-6.21176449)));
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testCalculateBidWithNullBias() {
        when(biddingRepository.getBias()).thenReturn(null);

        Map<String, String> features = new HashMap<>();
        features.put("deviceExtBrowser", "Firefox");
        features.put("bannerExtSize", "300x250");
        features.put("deviceLanguage", "de");
        features.put("deviceExtType", "tablet");

        assertThrows(BiasNotFoundException.class, () -> {
            biddingService.calculateBid(features);
        });
    }

    @Test
    public void testCalculateBidWithNullFeatures() {
        when(biddingRepository.getBias()).thenReturn(-6.21176449);

        Map<String, String> features = null;

        assertThrows(NullPointerException.class, () -> {
            biddingService.calculateBid(features);
        });
    }
}