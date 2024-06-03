package com.example.elasticsearchcrud.service;

import com.example.elasticsearchcrud.dtos.LoginRequest;
import com.example.elasticsearchcrud.dtos.LoginResponse;
import com.example.elasticsearchcrud.jwt.JwtUtils;
import com.example.elasticsearchcrud.model.User;
import com.example.elasticsearchcrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse register(User request) {

        User user = new User();
        user.setUserName(request.getUsername());
        user.setRole(request.getRole());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user = repository.save(user);

        String token=jwtService.generateTokenFromUsername(user);
        return new LoginResponse(token);

    }
    public LoginResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user =repository.findByUserName(request.getUsername()).orElseThrow();
        String token= jwtService.generateTokenFromUsername(user);
        return new LoginResponse(token);
    }
}
