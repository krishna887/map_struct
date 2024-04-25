package com.example.elasticsearchcrud.dtos;

import com.example.elasticsearchcrud.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
@Builder
public record BookResponse(
        String id,
        String title,

        int publicationYear,

        String authorName,

        String isbn) implements Serializable {


    public static BookResponse toBookResponse(Book book){
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .isbn(book.getIsbn())
                .publicationYear(book.getPublicationYear())
                .build();
    }

}
