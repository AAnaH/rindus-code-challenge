package com.example.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String getHelloWorld() {
        return "Hello, World!";
    }
}