package com.example.elasticsearchcrud.service;




import com.example.elasticsearchcrud.dtos.BookRequest;
import com.example.elasticsearchcrud.dtos.BookResponse;
import com.example.elasticsearchcrud.exception.custom.BookNotFoundException;
import com.example.elasticsearchcrud.mapper.BookMapper;
import com.example.elasticsearchcrud.model.Book;
import com.example.elasticsearchcrud.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
private final BookMapper bookMapper;

    private final BookRepository bookRepository;


    @Override
    public BookResponse create(BookRequest bookRequest) {
//I have used the StructMapper so this builder pattern is not needed

//        Book books= Book.builder()
//                .isbn(bookRequest.isbn())
//                .authorName(bookRequest.authorName())
//                .publicationYear(bookRequest.publicationYear())
//                .title(bookRequest.title())
//                .build();

var books=bookMapper.bookRequestToBook(bookRequest);
//        var books= modelMapper.map(bookRequest, Book.class);
             bookRepository.save(books);
         return     bookMapper.bookToBookResponse(books);
//             return  modelMapper.map(books, BookResponse.class);
//        return BookResponse.toBookResponse(books);

    }

    @Override
    public List<BookResponse> findAll() {
        List<Book> list= new ArrayList<>();
       bookRepository.findAll().forEach(list::add);
       return list.stream().map(bookMapper::bookToBookResponse).collect(Collectors.toList());
    }

    @Override
    public Optional<BookResponse> findById(Long id) {
//      var bookOpt=  bookRepository.findById(id).stream().map(bookMapper::bookToBookResponse).findFirst().orElse(null);
//      return Optional.ofNullable(bookOpt);
        var bookOpt=bookRepository.findById(id);
        if(bookOpt.isPresent()) {
//        return bookMapper.mapToResponseOptional(bookOpt);
            return bookOpt.map(bookMapper::bookToBookResponse);
        }
        else return Optional.empty();
       
    }

    @Override
    public BookResponse update(BookRequest bookRequest) {
        var books=bookMapper.bookRequestToBook(bookRequest);
        bookRepository.save(books);
        return    bookMapper.bookToBookResponse(books);

    }

    @Override
    public void delete(long id) {
        var bookOpt=bookRepository.findById(id);
        if(bookOpt.isPresent()) {
            bookRepository.deleteById(id);
        }
        else throw new BookNotFoundException("book of this id is not found");
    }


}

