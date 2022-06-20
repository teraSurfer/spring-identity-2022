package com.example.springidentity2022.dto;


import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

import javax.validation.Valid;

@Data
public class RegisterDTO {
    @JsonAlias("user")
    @Valid
    private UserDTO user;
}
