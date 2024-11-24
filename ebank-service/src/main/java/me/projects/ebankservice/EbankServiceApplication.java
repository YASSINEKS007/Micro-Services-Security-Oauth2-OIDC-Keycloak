package me.projects.ebankservice;

import me.projects.ebankservice.entities.BankAccount;
import me.projects.ebankservice.entities.Customer;
import me.projects.ebankservice.enums.AccountType;
import me.projects.ebankservice.repositories.BankAccountRepository;
import me.projects.ebankservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository) {
        return args -> {
            for (int j = 1; j <= 5; j++) {
                Customer customer = new Customer();
                customer.setName("Customer " + j);
                customer.setEmail("customer" + j + "@example.com");
                customer.setPhone("123456789" + j);

                customerRepository.save(customer);

                for (int i = 1; i <= 10; i++) {
                    BankAccount bankAccount = BankAccount.builder()
                            .createdAt(new Date())
                            .balance(i * 1000.0)
                            .accountType(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                            .currency("MAD")
                            .customer(customer)
                            .build();

                    bankAccountRepository.save(bankAccount);
                }
            }
        };
    }

}
