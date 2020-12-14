package com.example.biblePoke.service;

import com.example.biblePoke.model.CreateAccountRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateAccountService {

    private static final List<CreateAccountRequest> CREATE_ACCOUNT_REQUESTS = new ArrayList<>();

    public List<CreateAccountRequest> getAccounts() {
        return CreateAccountService.CREATE_ACCOUNT_REQUESTS;
    }


    public void createAccount(CreateAccountRequest createAccountRequest){
        CREATE_ACCOUNT_REQUESTS.add(createAccountRequest);
    }


}
