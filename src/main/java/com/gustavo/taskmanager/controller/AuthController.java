package com.gustavo.taskmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.gustavo.taskmanager.config.AppAuthProperties;
import com.gustavo.taskmanager.dto.LoginRequestDTO;
import com.gustavo.taskmanager.dto.LoginResponseDTO;
import com.gustavo.taskmanager.exception.UnauthorizedException;
import com.gustavo.taskmanager.security.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AppAuthProperties authProps;
    private final JwtService jwtService;

    public AuthController(AppAuthProperties authProps, JwtService jwtService) {
        this.authProps = authProps;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO dto) {
        if (!authProps.getUsername().equals(dto.getUsername()) || !authProps.getPassword().equals(dto.getPassword())) {
            throw new UnauthorizedException();
        }

        String token = jwtService.generateToken(dto.getUsername());
        return new LoginResponseDTO(token);
    }
}

