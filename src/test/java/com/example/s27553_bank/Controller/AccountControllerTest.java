package com.example.s27553_bank.Controller;

import com.example.s27553_bank.Model.Account;
import com.example.s27553_bank.Model.Currency;
import com.example.s27553_bank.Repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AccountControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @BeforeEach
    public void setUp() {
        accountRepository.clear();
    }
    @Test
    void shouldAddAcount() throws Exception{
        Account account = new Account("Kasia", "XYZ", 10.0,"123", Currency.PLN);
        webTestClient.post().uri("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(account))
                .exchange()
                .expectStatus().isEqualTo(201)
                .expectBody(Account.class)
                .returnResult().getResponseBody();
    }
}