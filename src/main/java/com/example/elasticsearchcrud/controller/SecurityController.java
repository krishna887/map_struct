package com.example.elasticsearchcrud.controller;
import com.example.elasticsearchcrud.config.SecurityConfig;
import com.example.elasticsearchcrud.dtos.LoginRequest;
import com.example.elasticsearchcrud.dtos.LoginResponse;
import com.example.elasticsearchcrud.jwt.JwtUtils;
import com.example.elasticsearchcrud.model.User;
import com.example.elasticsearchcrud.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final AuthenticationService authService;


    private  final  AuthenticationManager authenticationManager;
   private  final JwtUtils jwtUtils;
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }
   @PostMapping("/login")
   public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
       return ResponseEntity.ok(authService.login(request));
   }



}
