package com.example.elasticsearchcrud.controller;

import com.example.elasticsearchcrud.dtos.BookRequest;
import com.example.elasticsearchcrud.dtos.BookResponse;
import com.example.elasticsearchcrud.service.BookService;
import com.example.elasticsearchcrud.utill.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @PostMapping("/create")
public ResponseEntity<GenericResponse<BookResponse>> createBook(@RequestBody BookRequest bookRequest) {
//        return GenericResponse.<BookResponse>builder()
//                .message("Book Created Successfully")
//                .status(true)
//                .data(bookService.create(bookRequest))
//                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("costum headers can pssed here")
                .body( GenericResponse.success(bookService.create(bookRequest),"Book Created Successfully"));

}






}