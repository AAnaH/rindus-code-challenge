package com.example.myapp.controller;

import com.example.myapp.exception.BiasNotFoundException;
import com.example.myapp.service.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BiddingController {

    @Autowired
    private BiddingService biddingService;

    @PostMapping("/bid")
    public double getBid(@RequestBody Map<String, String> features) {
        return biddingService.calculateBid(features);
    }
    
    @ExceptionHandler(BiasNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBiasNotFoundException(BiasNotFoundException ex) {
        return ex.getMessage();
    }
}