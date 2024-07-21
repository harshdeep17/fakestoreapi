package com.example.firstproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class TestController {
    @GetMapping("/")
    String getAllUser(){
        return "Hey from all users";
    }
    @GetMapping("/{userId}")
    String getUser(@PathVariable String userId){
        return "Hey "+ userId;
    }
}
