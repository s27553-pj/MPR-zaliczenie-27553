package com.example.s27553_bank.Controller;

import com.example.s27553_bank.Exception.ValidationException;
import com.example.s27553_bank.Model.Account;
import com.example.s27553_bank.Service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private AccountService accountService;
    @PostMapping("/add")
    public ResponseEntity<Account> addAccount(@RequestBody Account account){
        try{
            Account result = accountService.add(account);
            return ResponseEntity.status(201).body(result);
        }catch (ValidationException exception){
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("/getByPesel/{pesel}")
    public ResponseEntity<Account> getByPesel(@PathVariable String pesel){
        return  ResponseEntity.of(accountService.getByPesel(pesel));
    }
    @GetMapping("/getGreaterThan/{accountsaldo}")
    public ResponseEntity<List<Account>> getGreaterThan(@PathVariable double accountsaldo){
        List<Account> accounts = accountService.getGreaterThan(accountsaldo);
        return  ResponseEntity.ok(accounts);
    }

}
