package com.example.elasticsearchcrud.dtos;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String tokenType= "Bearer ";
    private String  token ;
    public AuthResponseDto(String token){
        this.token=token;
    }
}
