package com.example.elasticsearchcrud.service;

import com.example.elasticsearchcrud.dtos.BookRequest;
import com.example.elasticsearchcrud.dtos.BookResponse;
import com.example.elasticsearchcrud.model.Book;

import java.util.List;

public interface BookService {

    BookResponse create(BookRequest bookRequest);
    List<BookResponse> findAll();
}
