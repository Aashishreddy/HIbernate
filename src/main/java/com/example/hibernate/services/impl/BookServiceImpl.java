package com.example.hibernate.services.impl;

import com.example.hibernate.domain.dto.BookDto;
import com.example.hibernate.domain.entities.BookEntity;
import com.example.hibernate.repository.BookRepository;
import com.example.hibernate.services.BookService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    @Override
    public BookEntity updateBook(String isbn, BookEntity bookEntity) {
        Optional<BookEntity> book = bookRepository.findById(isbn);
        BookEntity bookEntitytoUpdate = null;
        if (book.isPresent()) {
            bookEntitytoUpdate = book.get();
            bookEntitytoUpdate.setTitle(bookEntity.getTitle());
            bookEntitytoUpdate.setAuthorEntity(bookEntity.getAuthorEntity());
        }
        return bookRepository.save(bookEntitytoUpdate);
    }
}
