package com.example.elasticsearchcrud.utill;

import com.example.elasticsearchcrud.dtos.BookResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Builder
@Data
@AllArgsConstructor
public class GenericResponse<T> {
    private String message;
    private boolean status;
    private T data;


    public  static  <T> GenericResponse<T> empty(String message){
        return GenericResponse.<T>builder()
                .message(message)
                .status(false)
                .data(null)
                .build();
    }

    public  static  <T> GenericResponse<T> success(T data,String message){
        return GenericResponse.<T>builder()
                .message(message)
                .status(true)
                .data(data)
                .build();
    }
    public  static  <T> GenericResponse<T> error(String message){
        return GenericResponse.<T>builder()
                .message(message)
                .status(false)
                .data(null)
                .build();
    }
}

