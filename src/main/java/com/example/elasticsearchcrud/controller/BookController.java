package com.example.elasticsearchcrud.controller;

import com.example.elasticsearchcrud.dtos.BookRequest;
import com.example.elasticsearchcrud.dtos.BookResponse;
import com.example.elasticsearchcrud.exception.custom.BookNotFoundException;
import com.example.elasticsearchcrud.service.BookService;
import com.example.elasticsearchcrud.utill.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/")
public ResponseEntity<GenericResponse<BookResponse>> createBook(@RequestBody BookRequest bookRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("costum headers can pssed here")
                .body( GenericResponse.success(bookService.create(bookRequest),"Book Created Successfully"));

}
@GetMapping("/")
    public GenericResponse<List<BookResponse>>findAll(){
        return GenericResponse.<List<BookResponse>>builder()
                .message("Getting all Books")
                .status(true)
                .data(bookService.findAll())
                .build();
}
@GetMapping("/{id}")
    public ResponseEntity<GenericResponse<BookResponse>> getBookById(@PathVariable ("id") long id){
    return ResponseEntity.status(HttpStatus.CREATED)
            .header("costum headers can pssed here")
            .body( GenericResponse.success(bookService.findById(id).orElseThrow(null),"Get book by id"));
}


@PutMapping("/update")
public ResponseEntity<GenericResponse<BookResponse>> updateBook(@RequestBody BookRequest bookRequest) {

    return ResponseEntity.status(HttpStatus.CREATED)
            .body( GenericResponse.success(bookService.update(bookRequest),"Book updated Successfully"));

}
@DeleteMapping("/{id}")
public ResponseEntity deleteBook(@PathVariable  long id) {
        return ResponseEntity.ok("book deleted successfully");
    }
}