package com.example.s27553_bank.Repository;

import com.example.s27553_bank.Model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {
    List<Account> accountRepository = new ArrayList<>();

    public Account add(Account account) {
        accountRepository.add(account);
        return account;
    }

    public Optional<Account> getByPesel(String pesel) {
        return accountRepository.stream()
                .filter(account -> account.getPesel().equals(pesel))
                .findFirst();
    }

    public List<Account> getGreaterThan(double accountsaldo) {
        return accountRepository.stream()
                .filter(account -> account.getAccountsaldo() > accountsaldo)
                .toList();
    }
    public void clear(){
        accountRepository.clear();
    }
}
