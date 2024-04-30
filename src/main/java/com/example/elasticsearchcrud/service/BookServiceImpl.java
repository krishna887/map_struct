package com.example.elasticsearchcrud.service;



import static co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders.match;
import static co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders.matchPhrase;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.example.elasticsearchcrud.dtos.BookRequest;
import com.example.elasticsearchcrud.dtos.BookResponse;
import com.example.elasticsearchcrud.mapper.BookMapper;
import com.example.elasticsearchcrud.model.Book;
import com.example.elasticsearchcrud.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.stereotype.Service;
import org.springframework.data.elasticsearch.core.SearchHit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
private final BookMapper bookMapper;

    private final BookRepository bookRepository;

    private final ElasticsearchTemplate elasticsearchTemplate;

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
    public Optional<BookResponse> findById(String id) {
//      var bookOpt=  bookRepository.findById(id).stream().map(bookMapper::bookToBookResponse).findFirst().orElse(null);
//      return Optional.ofNullable(bookOpt);
        var bookOpt=bookRepository.findById(id);
        if(bookOpt.isPresent()) {
//        return bookMapper.mapToResponseOptional(bookOpt);
            return bookOpt.map(bookMapper::bookToBookResponse);
        }
        else return Optional.empty();
       
    }
    public List<BookResponse> findByTitleAndAuthor(String title, String author) {
        
        var books=bookRepository.getBookByAuthorNameAndTitle(author,title);
        return books.stream().map(bookMapper::bookToBookResponse).toList();
//        var criteria = QueryBuilders.bool(builder -> builder.must(
//                matchPhrase(queryAuthor -> queryAuthor.field("authorName").query(author)),
//                matchPhrase(queryTitle -> queryTitle.field("title").query(title))
//        ));
//
//        var response=elasticsearchTemplate.search(NativeQuery.builder().withQuery(criteria).build(), Book.class)
//                .stream().map(SearchHit::getContent).toList();
//
//        return response.stream().map(bookMapper::bookToBookResponse).toList();
    }
//

}

