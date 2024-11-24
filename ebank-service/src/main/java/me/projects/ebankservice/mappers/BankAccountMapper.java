package me.projects.ebankservice.mappers;

import me.projects.ebankservice.dtos.BankAccountDTO;
import me.projects.ebankservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapper {
    public BankAccountDTO bankAccountDTOFromBankAccount(BankAccount bankAccount) {
        if (bankAccount == null) {
            throw new IllegalArgumentException("bankAccount must not be null");
        }
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        BeanUtils.copyProperties(bankAccount, bankAccountDTO);
        return bankAccountDTO;
    }

    public BankAccount bankAccountFromBankAccountDTO(BankAccountDTO bankAccountDTO) {
        if (bankAccountDTO == null) {
            throw new IllegalArgumentException("bankAccountDTO must not be null");
        }
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountDTO, bankAccount);
        return bankAccount;
    }
}
