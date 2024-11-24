package me.projects.ebankservice.entities;

import jakarta.persistence.*;
import lombok.*;
import me.projects.ebankservice.enums.AccountType;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date createdAt;
    private Double balance;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private String currency;
    @ManyToOne
//    @JoinColumn(name = "customer_id")
    private Customer customer;
}
