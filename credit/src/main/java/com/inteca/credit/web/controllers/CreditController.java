package com.inteca.credit.web.controllers;


import com.inteca.credit.services.listeners.CreditService;
import lombok.RequiredArgsConstructor;
import model.CreditOrder;
import model.response.CreditResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/credits")
public class CreditController {

    private final CreditService creditService;

    @GetMapping
    public List<CreditResponseDto> getCredits() {

        return creditService.getCredits();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int createCredit(@RequestBody CreditOrder creditOrder) {

        return creditService.createCredit(creditOrder);
    }


}
