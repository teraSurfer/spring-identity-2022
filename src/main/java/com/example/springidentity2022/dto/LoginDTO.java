package com.example.springidentity2022.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    private String password;
}
