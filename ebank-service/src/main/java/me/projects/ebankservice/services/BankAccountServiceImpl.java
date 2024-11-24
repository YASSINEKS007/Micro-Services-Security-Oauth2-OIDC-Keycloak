package me.projects.ebankservice.services;

import lombok.AllArgsConstructor;
import me.projects.ebankservice.dtos.BankAccountDTO;
import me.projects.ebankservice.entities.BankAccount;
import me.projects.ebankservice.enums.AccountType;
import me.projects.ebankservice.exceptions.AccountNotFound;
import me.projects.ebankservice.mappers.BankAccountMapper;
import me.projects.ebankservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;

    @Override
    public BankAccount saveBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public BankAccount getBankAccountById(String id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new AccountNotFound());
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount updateBankAccount(BankAccount bankAccount, String id) {
        BankAccount existingBankAccount = getBankAccountById(id);
        if (bankAccount.getAccountType() != null)
            existingBankAccount.setAccountType(AccountType.valueOf(String.valueOf(bankAccount.getAccountType())));
        if (bankAccount.getBalance() != null) existingBankAccount.setBalance(bankAccount.getBalance());
        if (bankAccount.getCurrency() != null) existingBankAccount.setCurrency(bankAccount.getCurrency());

        return bankAccountRepository.save(existingBankAccount);
    }

    @Override
    public void deleteBankAccount(String id) {
        bankAccountRepository.deleteById(id);
    }

    @Override
    public BankAccountDTO saveBankAccountDTO(BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = bankAccountMapper.bankAccountFromBankAccountDTO(bankAccountDTO);
        bankAccount.setCreatedAt(new Date());
        return bankAccountMapper.bankAccountDTOFromBankAccount(bankAccountRepository.save(bankAccount));
    }

    @Override
    public BankAccountDTO getBankAccountDTOById(String id) {
        return bankAccountMapper.bankAccountDTOFromBankAccount(getBankAccountById(id));
    }

    @Override
    public List<BankAccountDTO> getAllBankAccountDTOs() {
        return getAllBankAccounts().stream()
                .map(bankAccount -> bankAccountMapper.bankAccountDTOFromBankAccount(bankAccount))
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountDTO updateBankAccountDTO(BankAccountDTO bankAccountDTO, String id) {
        BankAccount bankAccount = updateBankAccount(bankAccountMapper.bankAccountFromBankAccountDTO(bankAccountDTO), id);
        return bankAccountMapper.bankAccountDTOFromBankAccount(bankAccount);
    }
}
