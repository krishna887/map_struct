package com.example.elasticsearchcrud.controller;
import com.example.elasticsearchcrud.config.SecurityConfig;
import com.example.elasticsearchcrud.dtos.LoginRequest;
import com.example.elasticsearchcrud.dtos.LoginResponse;
import com.example.elasticsearchcrud.jwt.JwtUtils;
import com.example.elasticsearchcrud.model.User;
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

    private  final  AuthenticationManager authenticationManager;
   private  final JwtUtils jwtUtils;
   @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
       Authentication authentication;
       try{
           authentication=authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
       }catch (AuthenticationException e){
           Map<String ,Object> map= new HashMap<>();
           map.put("message","Bad credentials");
           map.put("status",false);
           return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
       }
       SecurityContextHolder.getContext().setAuthentication(authentication);
       UserDetails userDetails= (UserDetails) authentication.getPrincipal();
       String jwtToken= jwtUtils.generateTokenFromUsername(userDetails);
       List<String> roles = new ArrayList<>();
       for (GrantedAuthority item : userDetails.getAuthorities()) {
           String authority = item.getAuthority();
           roles.add(authority);
       }
       LoginResponse loginResponse= new LoginResponse(jwtToken, userDetails.getUsername(), roles);
       return ResponseEntity.ok(loginResponse);
   }



}
