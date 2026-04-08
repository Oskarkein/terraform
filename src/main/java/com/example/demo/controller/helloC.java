package com.example.demo.controller;

import com.example.demo.service.helloS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloC {

    @Autowired
    private helloS helloService;

    @GetMapping("/hello")
    public String hello() {
        return helloService.getHelloMessage();
    }
}
