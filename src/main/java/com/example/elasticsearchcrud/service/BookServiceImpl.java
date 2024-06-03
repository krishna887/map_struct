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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
private final BookMapper bookMapper;

    private final BookRepository bookRepository;


    @Override
    public BookResponse create(BookRequest bookRequest) {

var books=bookMapper.bookRequestToBook(bookRequest);
             bookRepository.save(books);
         return     bookMapper.bookToBookResponse(books);

    }

    @Override
    public List<BookResponse> findAll() {
        List<Book> list = new ArrayList<>(bookRepository.findAll());
       return list.stream().map(bookMapper::bookToBookResponse).collect(Collectors.toList());
    }

    @Override
    public BookResponse findById(Long id) {
        var bookOpt=bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("Book of this id is not found"));
        return bookMapper.bookToBookResponse(bookOpt);
    }

    @Override
    public BookResponse update(BookRequest bookRequest) {
        var books=bookMapper.bookRequestToBook(bookRequest);
        books.setAuthorName(bookRequest.authorName());
        books.setIsbn(bookRequest.isbn());
        books.setTitle(bookRequest.title());
        books.setPublicationYear(bookRequest.publicationYear());
        bookRepository.save(books);
        return  bookMapper.bookToBookResponse(books);

    }

    @Override
    public String delete(long id) {
        bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("Book of this Id is not found"));
        bookRepository.deleteById(id);
        return "Book Deleted Successfully";
    }


}

