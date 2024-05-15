package com.example.elasticsearchcrud.repository;

import com.example.elasticsearchcrud.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookRepository extends JpaRepository<Book, String> {
//    List<Book> findByAuthorName(String authorName);
//
//    Optional<Book> findByIsbn(String isbn);

}
