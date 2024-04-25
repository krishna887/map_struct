package com.example.elasticsearchcrud.service;



import static co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders.match;

import com.example.elasticsearchcrud.dtos.BookRequest;
import com.example.elasticsearchcrud.dtos.BookResponse;
import com.example.elasticsearchcrud.mapper.BookMapper;
import com.example.elasticsearchcrud.model.Book;
import com.example.elasticsearchcrud.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
private final BookMapper bookMapper;

    private final BookRepository bookRepository;

    private final ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public BookResponse create(BookRequest bookRequest) {
//
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
//    public List<Book> findByTitleAndAuthor(String title, String author) {
//        var criteria = QueryBuilders.bool(builder -> builder.must(
//                match(queryAuthor -> queryAuthor.field("authorName").query(author)),
//                match(queryTitle -> queryTitle.field("title").query(title))
//        ));
//
//        return elasticsearchTemplate.search(NativeQuery.builder().withQuery(criteria).build(), Book.class)
//                .stream().map(SearchHit::getContent).toList();
//    }
//
//    public Optional<Book> findBookById(String id) {
//       return bookRepository.findById(id);
//    }
}

