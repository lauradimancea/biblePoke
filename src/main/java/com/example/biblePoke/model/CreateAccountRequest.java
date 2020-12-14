package com.example.biblePoke.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateAccountRequest {

 //   @NotNull
    @Size(min = 8, max = 20, message = "length between 8 and 20 characters")
    private String username;

    @NotNull
    @Size(min = 8, max = 20, message = "length between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "must contain at least capital letter, one special character and one digit")
    private String password;

    @NotNull
    @Size(min=5,max=100)
    @Email
    private String email;
}
