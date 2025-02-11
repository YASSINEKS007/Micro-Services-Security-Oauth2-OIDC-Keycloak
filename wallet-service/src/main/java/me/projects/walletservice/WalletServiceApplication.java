package me.projects.walletservice;

import me.projects.walletservice.entities.Currency;
import me.projects.walletservice.entities.Wallet;
import me.projects.walletservice.repositories.CurrencyRepository;
import me.projects.walletservice.repositories.WalletRepository;
import me.projects.walletservice.repositories.WalletTransactionRepository;
import me.projects.walletservice.services.CurrencyServiceImpl;
import me.projects.walletservice.services.WalletServiceImpl;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class WalletServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletServiceApplication.class, args);
    }

    @Bean
    KeycloakRestTemplate restTemplate(KeycloakClientRequestFactory keycloakClientRequestFactory){
        return new KeycloakRestTemplate(keycloakClientRequestFactory);
    }
    @Bean
    CommandLineRunner start(CurrencyServiceImpl currencyService,
                            WalletServiceImpl walletService,
                            CurrencyRepository currencyRepository,
                            WalletRepository walletRepository,
                            WalletTransactionRepository walletTransactionRepository){
        return args -> {
            currencyService.loadContinentsAndCountries();
            currencyService.loadCurrencies();
            currencyService.setCurrenciesPrices();
            List<String> currencies= Arrays.asList("MAD","EUR","USD","CAD");
            currencies.forEach(cur->{
                Currency currency=currencyRepository.findByCode(cur);
                Wallet wallet= Wallet.builder()
                        .id(UUID.randomUUID().toString())
                        .currency(currency)
                        .balance(1000)
                        .createdAt(System.currentTimeMillis())
                        .userId("user1")
                        .build();
                wallet=walletRepository.save(wallet);
            });
            var walletList = walletRepository.findAll();
            for (int i = 0; i < walletList.size()-1; i++) {
                var wal1=walletList.get(i);
                var wal2=walletList.get(i+1);
                //walletService.walletTransfer(wal1.getId(), wal2.getId(),100);
            }
            walletRepository.findAll().forEach(wallet -> {
                System.out.println("*********************");
                System.out.println(wallet.getId());
                System.out.println(wallet.getBalance());
                System.out.println("*******************");
            });
        };
    }

}
