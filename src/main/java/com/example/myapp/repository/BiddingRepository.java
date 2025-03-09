package com.example.myapp.repository;

import java.util.Map;

public interface BiddingRepository {
    Double getCoefficient(String key);
    Double getBias();
}