package com.skm.accounts.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {


    @GetMapping("/")
    public String sayhi(){
        System.out.println("Hello");
        return "hello";
    }

}