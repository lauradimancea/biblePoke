package com.example.biblePoke.controller;

import com.example.biblePoke.model.CreateAccountRequest;
import com.example.biblePoke.service.CreateAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CreateAccountController {

    private final CreateAccountService createAccountService;

    @Autowired
    public CreateAccountController(CreateAccountService createAccountService){
        this.createAccountService = createAccountService;
    }
    @PostMapping(path = "/account", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<String> createAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest){

        createAccountService.createAccount(createAccountRequest);

        return ResponseEntity.ok("Account Created");

    }

    @GetMapping(path = "/accounts")
    public HttpEntity<List<CreateAccountRequest>> getAllAccounts(){

        return ResponseEntity.ok(createAccountService.getAccounts());
    }

}
