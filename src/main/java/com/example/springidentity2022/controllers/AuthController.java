package com.example.springidentity2022.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springidentity2022.dto.LoginDTO;
import com.example.springidentity2022.dto.RegisterDTO;
import com.example.springidentity2022.models.User;
import com.example.springidentity2022.services.AuthService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO) {
        log.info("User attempted login with {}, {}", loginDTO.getEmail(),
                loginDTO.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),
                        loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<User> register(@RequestBody @Valid RegisterDTO registerDTO) {
        User user = authService.createUser(registerDTO.getUser());
        log.info("user -> {}", user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping("/principal")
    public ResponseEntity<UserDetails> getPrincipal() {
        Object principal = SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal();
        UserDetails user = null;
        if(principal instanceof UserDetails) {
            user = ((UserDetails) principal);
            log.info("found user details = {}", user);
        }

        return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
    }

}
