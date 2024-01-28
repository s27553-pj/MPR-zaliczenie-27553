package com.example.s27553_bank.Service;

import com.example.s27553_bank.Exception.ValidationException;
import com.example.s27553_bank.Model.Account;
import com.example.s27553_bank.Model.Currency;
import com.example.s27553_bank.Repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    public Account add(Account account){
        if(account.getName()==null || account.getName().isEmpty()) {
            throw new ValidationException("Imie nie moze byc puste");
        }
        if(account.getSurname()==null || account.getSurname().isEmpty()){
            throw new ValidationException("Nazwisko nie moze byc puste");
        }
        if(account.getPesel()==null || account.getPesel().isEmpty()){
            throw new ValidationException("pesel nie moze byc pusty");
        }
        if(account.getAccountsaldo()==null || account.getAccountsaldo()<0){
            throw new ValidationException("Saldo nie moze byc mniejsze od 0");
        }
        if(account.getCurrency()==null || !(account.getCurrency().equals(Currency.PLN) || account.getCurrency().equals(Currency.EUR) || account.getCurrency().equals(Currency.USD))){
            throw  new ValidationException("Nieprawidlowa waluta");
        }
        return accountRepository.add(account);
    }
    public Optional<Account> getByPesel(String pesel){
        return accountRepository.getByPesel(pesel);
    }
    public List<Account> getGreaterThan(double accountsaldo){
        return accountRepository.getGreaterThan(accountsaldo);
    }
}
