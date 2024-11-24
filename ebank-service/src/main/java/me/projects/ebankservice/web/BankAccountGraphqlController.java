package me.projects.ebankservice.web;

import lombok.AllArgsConstructor;
import me.projects.ebankservice.dtos.BankAccountDTO;
import me.projects.ebankservice.services.BankAccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class BankAccountGraphqlController {
    private BankAccountService bankAccountService;

    @QueryMapping
    public List<BankAccountDTO> accountsList() {
        return bankAccountService.getAllBankAccountDTOs();
    }

    @QueryMapping
    public BankAccountDTO accountById(@Argument String id) {
        return bankAccountService.getBankAccountDTOById(id);
    }

    @MutationMapping
    public BankAccountDTO createBankAccount(@Argument BankAccountDTO bankAccountDTO) {
        return bankAccountService.saveBankAccountDTO(bankAccountDTO);
    }

    @MutationMapping
    public void deleteBankAccount(@Argument String id) {
        bankAccountService.deleteBankAccount(id);
    }

    @MutationMapping
    public BankAccountDTO updateBankAccount(@Argument String id, @Argument BankAccountDTO bankAccountDTO) {
        return bankAccountService.updateBankAccountDTO(bankAccountDTO, id);
    }
}
