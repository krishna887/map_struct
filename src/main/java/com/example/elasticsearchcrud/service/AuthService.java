package com.example.elasticsearchcrud.service;

import com.example.elasticsearchcrud.dtos.AuthResponseDto;
import com.example.elasticsearchcrud.dtos.LoginDto;
import com.example.elasticsearchcrud.dtos.RegisterDto;
import com.example.elasticsearchcrud.model.AppUser;
import com.example.elasticsearchcrud.model.Roles;
import com.example.elasticsearchcrud.repository.AppUserRepository;
import com.example.elasticsearchcrud.repository.RoleRepository;
import com.example.elasticsearchcrud.security.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator generator;
    public String register(RegisterDto registerDto){
        if(appUserRepository.existsByUsername(registerDto.getUsername())){
            return  "User name is already taken!";
        }
        Roles role= roleRepository.findByRole(registerDto.getRole()).get();
        AppUser appUser= AppUser.builder()
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .roles(Collections.singletonList(role))
                .build();
        appUserRepository.save(appUser);
        return "User Registration Success!";
    }
    public AuthResponseDto login(LoginDto loginDto){
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token =generator.generateToken(loginDto);
        return new AuthResponseDto(token);
    }
}
