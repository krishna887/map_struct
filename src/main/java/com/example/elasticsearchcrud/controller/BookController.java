package com.example.elasticsearchcrud.controller;

import com.example.elasticsearchcrud.dtos.BookRequest;
import com.example.elasticsearchcrud.dtos.BookResponse;
import com.example.elasticsearchcrud.service.BookService;
import com.example.elasticsearchcrud.utill.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @PostMapping("/create")
public ResponseEntity<GenericResponse<BookResponse>> createBook(@RequestBody BookRequest bookRequest) {
//       this is the type of generic response sending process
//
//        return GenericResponse.<BookResponse>builder()
//                .message("Book Created Successfully")
//                .status(true)
//                .data(bookService.create(bookRequest))
//                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("costum headers can pssed here")
                .body( GenericResponse.success(bookService.create(bookRequest),"Book Created Successfully"));

}
@GetMapping("/all-books")
    public GenericResponse<List<BookResponse>>findAll(){
        return GenericResponse.<List<BookResponse>>builder()
                .message("Getting all Books")
                .status(true)
                .data(bookService.findAll())
                .build();
}
@GetMapping("/{id}")
    public ResponseEntity<GenericResponse<BookResponse>> getBookById(@PathVariable ("id") String id){
    return ResponseEntity.status(HttpStatus.CREATED)
            .header("costum headers can pssed here")
            .body( GenericResponse.success(bookService.findById(id).orElse(null),"Get book by id"));
}






}