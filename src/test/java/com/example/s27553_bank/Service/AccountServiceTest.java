package com.example.s27553_bank.Service;

import com.example.s27553_bank.Exception.ValidationException;
import com.example.s27553_bank.Model.Account;
import com.example.s27553_bank.Model.Currency;
import com.example.s27553_bank.Repository.AccountRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {
    private static AccountService accountService;
    private static AccountRepository accountRepository;
    @BeforeAll
    public static void setUp(){
        accountRepository=new AccountRepository();
        accountService= new AccountService(accountRepository);
    }
    @Test
    void shouldAddNewAccount(){
        Account account = new Account("Jan", "Nowak",100.00,"134", Currency.PLN);
        Account result = assertDoesNotThrow(()->accountService.add(account));
        assertEquals(result.getName(),account.getName());
        assertEquals(result.getSurname(),account.getSurname());
        assertEquals(result.getAccountsaldo(),account.getAccountsaldo());
        assertEquals(result.getPesel(),account.getPesel());
        assertEquals(result.getCurrency(), account.getCurrency());
    }
    @Test
    void shouldThrowValidationException(){
        Account account = new Account("","ZZZ", 12.0,"341", Currency.PLN);
        ValidationException validationException =assertThrows(ValidationException.class, ()-> accountService.add(account));
        assertEquals(validationException.getMessage(),"Imie nie moze byc puste");
    }
    @Test
    void shouldThrowValidExceptionForNegativeBalance(){
        Account account = new Account("Jan", "Nowak", -50.0, "456", Currency.PLN);
        ValidationException validationException = assertThrows(ValidationException.class, ()-> accountService.add(account));
        assertEquals(validationException.getMessage(),"Saldo nie moze byc mniejsze od 0");
    }
    @Test
    void shouldThrowValidationExceptionForBadCurrency(){
        Account account = new Account("Kasia", "Kowalska", 120.00, "678", null);
        ValidationException validationException = assertThrows(ValidationException.class, ()-> accountService.add(account));
        assertEquals(validationException.getMessage(),"Nieprawidlowa waluta");

    }

}