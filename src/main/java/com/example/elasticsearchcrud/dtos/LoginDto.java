package com.example.elasticsearchcrud.dtos;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
}