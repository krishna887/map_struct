package com.example.elasticsearchcrud.exception.custom;


public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String msg){
        super(msg);
    }
}
