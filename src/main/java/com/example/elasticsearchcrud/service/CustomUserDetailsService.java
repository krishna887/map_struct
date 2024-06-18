package com.example.elasticsearchcrud.service;

import com.example.elasticsearchcrud.model.AppUser;
import com.example.elasticsearchcrud.model.Roles;
import com.example.elasticsearchcrud.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       AppUser appUser=appUserRepository.findByUsername(username).orElseThrow(()->
               new UsernameNotFoundException("User not found of this Name"));

       return new User(appUser.getUsername(),appUser.getPassword(),mapRolesToAuthority(appUser.getRoles()));
    }
    private Collection<GrantedAuthority> mapRolesToAuthority(List<Roles> roles){
        return roles.stream().map(role->new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }
}
