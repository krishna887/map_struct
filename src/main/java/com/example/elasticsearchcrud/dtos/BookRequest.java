package com.example.elasticsearchcrud.dtos;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record BookRequest(
        String title,

        int publicationYear,

        String authorName,

        String isbn) implements Serializable {
}
