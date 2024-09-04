package com.example.hibernate.controllers;

import com.example.hibernate.TestDataUtil;
import com.example.hibernate.domain.entities.AuthorEntity;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc //instance of mockMvc and places into the application context
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorControllerIntegrationTests {

    private MockMvc mockMvc;

    // to generate Json for Author object
    private ObjectMapper objectMapper;

    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateAuthorVerifyHttpStatusCode() throws Exception {

        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        authorEntity.setId(null);
        String authorJson = objectMapper.writeValueAsString(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testCreateAuthorVerifyJsonParams() throws Exception {

        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        authorEntity.setId(null);
        String authorJson = objectMapper.writeValueAsString(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Ashihs")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(80)
        );
    }

    @Test
    public void testListAllAuthorsAndVerifyHttpStatus() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/getAll")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                        MockMvcResultMatchers.status().isOk()
                );
    }

    @Test
    public void testListAllAuthorsReturnsListOfAuthors() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/getAll")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testChecksTheFirstAuthor() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/getAll")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("AReddy")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value(27)
        );
    }

    // TODO: Integration Tests for FindOneByID, HTTPStatus and Params






















}
