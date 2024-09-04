package com.example.hibernate.mappers.impl;

import com.example.hibernate.domain.dto.BookDto;
import com.example.hibernate.domain.entities.BookEntity;
import com.example.hibernate.mappers.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements Mapper<BookEntity, BookDto> {

    //ModelMapper is used to convert DTO to Entities and vice-versa.
    //ObjectMapper is used to convert Objects to json and vice-versa.

    private ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDto mapTo(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookEntity mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }
}
