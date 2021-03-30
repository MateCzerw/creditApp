package com.inteca.credit.web.controllers;


import com.inteca.credit.services.listeners.CreditService;
import lombok.RequiredArgsConstructor;
import model.CreditOrder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/credits")
public class CreditController {

    private final CreditService creditService;

    @GetMapping
    public void getCredit(CreditOrder creditOrder){


    }


    @PostMapping
    public int createCredit(@RequestBody CreditOrder creditOrder){

        return creditService.newCreditOrder(creditOrder);
    }


}
