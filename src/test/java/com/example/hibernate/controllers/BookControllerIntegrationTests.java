package com.example.hibernate.controllers;

import com.example.hibernate.TestDataUtil;
import com.example.hibernate.domain.entities.AuthorEntity;
import com.example.hibernate.domain.entities.BookEntity;
import com.example.hibernate.repository.AuthorRepository;
import com.example.hibernate.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) //Clears data after each test method
public class BookControllerIntegrationTests {

    private MockMvc mockMvc;
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Test
    public void createBookAndVerifyHttpStatusCreated() throws Exception {
        AuthorEntity author = TestDataUtil.createTestAuthor();
        authorRepository.save(author);
        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(author);
        bookRepository.save(bookEntity);

        //convert bookEntity to json
        String bookJson = objectMapper.writeValueAsString(bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/books/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void createBookAndVerifyParams() throws Exception {
        AuthorEntity author = TestDataUtil.createTestAuthor();
        authorRepository.save(author);
        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(author);
        bookRepository.save(bookEntity);

        //convert bookEntity to json
        String bookJson = objectMapper.writeValueAsString(bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/books/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("ISBN")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("To victory")
        );
    }


}
