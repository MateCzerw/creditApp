package com.inteca.credit.domain;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditOrder {

    private UUID id;

    private String creditName;

    public CreateCreditStatusEnum status;
}
