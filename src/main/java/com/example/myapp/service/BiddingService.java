package com.example.myapp.service;

import java.util.Map;

public interface BiddingService {
    double calculateBid(Map<String, String> features);
}