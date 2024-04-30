package com.example.elasticsearchcrud.repository;

import com.example.elasticsearchcrud.dtos.BookResponse;
import com.example.elasticsearchcrud.model.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends ElasticsearchRepository<Book, String> {
//    List<Book> findByAuthorName(String authorName);
//
//    Optional<Book> findByIsbn(String isbn);


    List<Book> getBookByAuthorNameAndTitle(String author,String title);

}
