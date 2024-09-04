package com.example.hibernate.services;

import com.example.hibernate.domain.dto.BookDto;
import com.example.hibernate.domain.entities.BookEntity;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    public BookEntity createBook(BookEntity bookEntity);
    public BookEntity updateBook(String isbn, BookEntity bookEntity);
}
