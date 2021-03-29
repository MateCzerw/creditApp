package com.inteca.credit.web.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/credits/")
public class CreditController {

    @GetMapping
    public void getCredit(){

    }


    @PostMapping
    public void createCredit(){

    }


}
