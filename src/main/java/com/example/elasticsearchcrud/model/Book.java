package com.example.elasticsearchcrud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "books")
public class Book {

    @Id
    private String id;

    private String title;

    private int publicationYear;

    private String authorName;

    private String isbn;
}
