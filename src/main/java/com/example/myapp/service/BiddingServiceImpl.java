package com.example.myapp.service;

import com.example.myapp.exception.BiasNotFoundException;
import com.example.myapp.repository.BiddingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BiddingServiceImpl implements BiddingService {

    @Autowired
    private BiddingRepository biddingRepository;

    @Override
    public double calculateBid(Map<String, String> features) {
        Double bias = biddingRepository.getBias();
        if (bias == null) {
            throw new BiasNotFoundException("Bias not found in the model");
        }

        double sum = bias;

        for (Map.Entry<String, String> entry : features.entrySet()) {
            String key = entry.getKey() + "=" + entry.getValue();
            Double coefficient = biddingRepository.getCoefficient(key);
            if (coefficient != null) {
                sum += coefficient;
            }
        }

        return logisticFunction(sum);
    }

    private double logisticFunction(double x) {
        return 1 / (1 + Math.exp(-x));
    }
}