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
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
//
//    @ExceptionHandler(BookNotFoundException.class)
//    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException e) {
//        return new ResponseEntity<>("Book Not found: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(BookNotFoundException e) {
        return new ResponseEntity<>("Resource not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @GetMapping("/custom")
    public ResponseEntity<String> customException() {
        throw new BookNotFoundException("Book not found exception is called");
    }
    @PostMapping("/create")
public ResponseEntity<GenericResponse<BookResponse>> createBook(@RequestBody BookRequest bookRequest) {
        System.out.println("first or second");
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
@DeleteMapping("/deleted")
public ResponseEntity deleteBook(@PathVariable ("id") long id) {
        return ResponseEntity.ok("book deleted successfully");
    }
}