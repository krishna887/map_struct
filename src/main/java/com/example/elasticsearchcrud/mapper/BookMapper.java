package com.example.elasticsearchcrud.mapper;

import com.example.elasticsearchcrud.dtos.BookRequest;
import com.example.elasticsearchcrud.dtos.BookResponse;
import com.example.elasticsearchcrud.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface BookMapper {
Book bookRequestToBook(BookRequest bookRequest);
BookRequest bookToBookRequest(Book book);
Book bookResponseToBook(BookResponse bookResponse);
BookResponse bookToBookResponse(Book book);


}
