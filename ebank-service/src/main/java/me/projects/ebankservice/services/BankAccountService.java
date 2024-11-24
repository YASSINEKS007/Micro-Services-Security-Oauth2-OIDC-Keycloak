package me.projects.ebankservice.services;

import me.projects.ebankservice.dtos.BankAccountDTO;
import me.projects.ebankservice.entities.BankAccount;

import java.util.List;

public interface BankAccountService {
    BankAccount saveBankAccount(BankAccount bankAccount);

    BankAccount getBankAccountById(String id);

    List<BankAccount> getAllBankAccounts();

    BankAccount updateBankAccount(BankAccount bankAccount, String id);

    void deleteBankAccount(String id);

    BankAccountDTO saveBankAccountDTO(BankAccountDTO bankAccountDTO);

    BankAccountDTO getBankAccountDTOById(String id);

    List<BankAccountDTO> getAllBankAccountDTOs();

    BankAccountDTO updateBankAccountDTO(BankAccountDTO bankAccountDTO, String id);
}
