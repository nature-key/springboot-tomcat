package com.springboot.tomcat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User {
    @GetMapping(value = "hello")
    public  String hello(){

        return "hello";
    }
}
