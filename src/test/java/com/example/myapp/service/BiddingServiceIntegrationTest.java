package com.example.myapp.service;

import com.example.myapp.repository.BiddingRepository;
import com.example.myapp.repository.BiddingRepositoryImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import redis.embedded.RedisServer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class BiddingServiceIntegrationTest {

    private static RedisServer redisServer;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private BiddingService biddingService;

    @BeforeAll
    public static void startRedis() throws IOException {
        redisServer = new RedisServer(6379);
        redisServer.start();
    }

    @AfterAll
    public static void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }

    @BeforeEach
    public void setUp() {
        // Cargar el modelo de ejemplo en Redis
        redisTemplate.opsForHash().put("model", "bannerExtSize=0x0", 1.616892819);
        redisTemplate.opsForHash().put("model", "bannerExtSize=flexible", 1.578777976);
        redisTemplate.opsForHash().put("model", "deviceLanguage=es", 1.134030757);
        redisTemplate.opsForHash().put("model", "deviceLanguage=en", 1.111139494);
        redisTemplate.opsForHash().put("model", "deviceLanguage=it", 1.06355885);
        redisTemplate.opsForHash().put("model", "deviceExtType=tablet", 0.7294739471);
        redisTemplate.opsForHash().put("model", "deviceLanguage=ar", 0.6317743188);
        redisTemplate.opsForHash().put("model", "deviceLanguage=fr", 0.4418820908);
        redisTemplate.opsForHash().put("model", "deviceLanguage=ro", 0.3260918906);
        redisTemplate.opsForHash().put("model", "deviceExtBrowser=Chrome", 0.2102317412);
        redisTemplate.opsForHash().put("model", "bannerExtSize=800x250", 0.1692596709);
        redisTemplate.opsForHash().put("model", "bannerExtSize=300x600", 0.1628927345);
        redisTemplate.opsForHash().put("model", "deviceLanguage=ru", -0.0310927102);
        redisTemplate.opsForHash().put("model", "deviceExtBrowser=Safari", -0.0360865458);
        redisTemplate.opsForHash().put("model", "deviceExtType=mobile", -0.0372569973);
        redisTemplate.opsForHash().put("model", "deviceExtBrowser=IE", -0.0406608564);
        redisTemplate.opsForHash().put("model", "deviceExtType=desktop", -0.1037572241);
        redisTemplate.opsForHash().put("model", "deviceExtBrowser=Firefox", -0.1131013246);
        redisTemplate.opsForHash().put("model", "deviceLanguage=de", -0.1935418172);
        redisTemplate.opsForHash().put("model", "deviceLanguage=hu", -0.4186695043);
        redisTemplate.opsForHash().put("model", "deviceLanguage=tr", -0.4304739397);
        redisTemplate.opsForHash().put("model", "bannerExtSize=320x50", -0.4556144288);
        redisTemplate.opsForHash().put("model", "bannerExtSize=120x600", -0.5110433495);
        redisTemplate.opsForHash().put("model", "bannerExtSize=160x600", -0.5334565661);
        redisTemplate.opsForHash().put("model", "deviceLanguage=hr", -0.5559580767);
        redisTemplate.opsForHash().put("model", "bannerExtSize=728x90", -0.6115154594);
        redisTemplate.opsForHash().put("model", "bannerExtSize=300x250", -0.6282185905);
        redisTemplate.opsForHash().put("model", "deviceLanguage=sk", -1.266130497);
        redisTemplate.opsForHash().put("model", "deviceLanguage=pl", -1.266865912);
        redisTemplate.opsForHash().put("model", "deviceLanguage=cs", -1.640872261);
        redisTemplate.opsForHash().put("model", "bias", -6.21176449);
    }

    @Test
    public void testCalculateBid() {
        // Verificar que el modelo se ha cargado correctamente en Redis
        assertNotNull(redisTemplate.opsForHash().get("model", "bias"));

        Map<String, String> features = new HashMap<>();
        features.put("deviceExtBrowser", "Firefox");
        features.put("bannerExtSize", "300x250");
        features.put("deviceLanguage", "de");
        features.put("deviceExtType", "tablet");

        double result = biddingService.calculateBid(features);
        assertEquals(0.0016306374, result, 1e-9);
    }
}