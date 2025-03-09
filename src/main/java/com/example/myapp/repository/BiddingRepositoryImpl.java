package com.example.myapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BiddingRepositoryImpl implements BiddingRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Double getCoefficient(String key) {
        return (Double) redisTemplate.opsForHash().get("model", key);
    }

    @Override
    public Double getBias() {
        return (Double) redisTemplate.opsForHash().get("model", "bias");
    }
}