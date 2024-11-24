package me.projects.ebankservice.web;

import lombok.AllArgsConstructor;
import me.projects.ebankservice.dtos.BankAccountDTO;
import me.projects.ebankservice.services.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class BankAccountRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/bank-accounts")
    public List<BankAccountDTO> getAllBankAccountsDTOS() {
        return bankAccountService.getAllBankAccountDTOs();
    }

    @GetMapping("/bank-accounts/{id}")
    public ResponseEntity<BankAccountDTO> getBankAccountDTO(@PathVariable String id) {
        return new ResponseEntity<>(bankAccountService.getBankAccountDTOById(id), HttpStatus.OK);
    }

    @PostMapping("/save-bank-account")
    public BankAccountDTO saveBankAccountDTO(@RequestBody BankAccountDTO bankAccountDTO) {
        return bankAccountService.saveBankAccountDTO(bankAccountDTO);
    }

    @PutMapping("/update-bank-account/{id}")
    public BankAccountDTO updateBankAccountDTO(@PathVariable String id, @RequestBody BankAccountDTO bankAccountDTO) {
        return bankAccountService.updateBankAccountDTO(bankAccountDTO, id);
    }

    @DeleteMapping("/delete-bank-account/{id}")
    public void deleteBankAccount(@PathVariable String id) {
        bankAccountService.deleteBankAccount(id);
    }
}
