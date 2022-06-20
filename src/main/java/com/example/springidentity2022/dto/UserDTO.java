package com.example.springidentity2022.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {
    
    @NotBlank
    private String name;

    @NotBlank(message = "email cannot be blank")
    @Email()
    private String email;

    @NotBlank
    @Size(min = 6, max = 20, message = "Password should be between 6 and 20 characters")
    @Pattern(regexp = ".*")
    private String password;

    @Override
    public String toString() {
        return "UserDTO {email=" + email + ", name=" + name + ", password=" + password + "}";
    }
    
}
