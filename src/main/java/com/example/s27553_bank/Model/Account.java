package com.example.s27553_bank.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private String name;
    private String surname;
    private Double accountsaldo;
    private String pesel;
    private Currency currency;
}
