package com.inteca.credit.domain;


import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Credit {

    private int id;

    private String creditName;

}
