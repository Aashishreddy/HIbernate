package com.example.hibernate.controllers;

import com.example.hibernate.domain.dto.BookDto;
import com.example.hibernate.domain.entities.BookEntity;
import com.example.hibernate.services.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    private ModelMapper modelMapper;

    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) throws JsonProcessingException {

        //use ObjectMapper to convert Dto to Entity
        BookEntity bookEntity = modelMapper.map(bookDto, BookEntity.class);
        BookEntity savedBookDto = bookService.createBook(bookEntity);
        BookDto resultbookDto = modelMapper.map(savedBookDto, BookDto.class);

        return new ResponseEntity<>(resultbookDto, HttpStatus.CREATED);
    }

    @PutMapping("/create/{isbn}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("isbn") String isbn,
                                              @RequestBody BookDto bookDto) throws JsonProcessingException {

        BookEntity bookEntity = modelMapper.map(bookDto, BookEntity.class);
        BookEntity updateBookEntity = bookService.updateBook(isbn, bookEntity);
        BookDto resultBookDto = modelMapper.map(updateBookEntity, BookDto.class);

        return new ResponseEntity<>(resultBookDto, HttpStatus.CREATED);
    }

    /**
     * get list of Books and also 2 Integration Tests(HttpStatusCode, firstBook)
     */

    /**
     * findBookByIsbn and also 2 Integration Tests
     */
























}
