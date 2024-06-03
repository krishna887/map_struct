package com.example.elasticsearchcrud.controller;
import com.example.elasticsearchcrud.dtos.BookRequest;
import com.example.elasticsearchcrud.dtos.BookResponse;
import com.example.elasticsearchcrud.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/")
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.create(bookRequest));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<BookResponse>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("id") long id) {
        return ResponseEntity.ok(bookService.findById(id));

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.update(bookRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long id) {
        bookService.delete(id);
        return ResponseEntity.ok("book deleted successfully");
    }
}