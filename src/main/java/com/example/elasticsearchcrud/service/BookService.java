package com.example.elasticsearchcrud.service;

import com.example.elasticsearchcrud.dtos.BookRequest;
import com.example.elasticsearchcrud.dtos.BookResponse;

public interface BookService {

    BookResponse create(BookRequest bookRequest);
}
