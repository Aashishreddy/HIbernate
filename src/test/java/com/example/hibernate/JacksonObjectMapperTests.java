package com.example.hibernate;

import com.example.hibernate.domain.entities.AuthorEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonObjectMapperTests {

    @Test
    public void testJacksonJavaObjectToJson() throws JsonProcessingException {

        AuthorEntity authorEntity = AuthorEntity.builder().id(1L).name("Ash").age(25).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResult = objectMapper.writeValueAsString(authorEntity);
        assertThat(jsonResult).isEqualTo("{\"id\":1,\"name\":\"Ash\",\"age\":25}");
    }

    @Test
    public void testJacksonJsonToJavaObject() throws JsonProcessingException {
        AuthorEntity authorEntity = AuthorEntity.builder().id(1L).name("Ash").age(25).build();
        String json = "{\"id\":1,\"name\":\"Ash\",\"age\":25}";
        ObjectMapper objectMapper = new ObjectMapper();
        AuthorEntity result  = objectMapper.readValue(json, AuthorEntity.class);
        assertThat(result).isEqualTo(authorEntity);

    }
}
