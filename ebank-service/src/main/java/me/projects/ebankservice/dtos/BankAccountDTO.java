package me.projects.ebankservice.dtos;

import lombok.*;
import me.projects.ebankservice.entities.Customer;
import me.projects.ebankservice.enums.AccountType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BankAccountDTO {
    private String id;
    private Double balance;
    private AccountType accountType;
    private String currency;
    private Customer customer;
}
