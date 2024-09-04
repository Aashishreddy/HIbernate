package com.example.hibernate;


import com.example.hibernate.domain.dto.AuthorDto;
import com.example.hibernate.domain.dto.BookDto;
import com.example.hibernate.domain.entities.AuthorEntity;
import com.example.hibernate.domain.entities.BookEntity;

public final class TestDataUtil {

    /***
     *
     * Utility class
     * Always follows a pattern of being final.
     *
     */

    public static AuthorEntity createTestAuthor() {
        return AuthorEntity.builder().name("Ashihs").age(80).build(); // removing Id because it is already being set by GeneratedValue.
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder().name("Akhil").age(40).build();
    }

    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder().name("Shanthan").age(25).build();
    }


    public static BookEntity createTestBookEntityA(final AuthorEntity authorEntity) {
        return BookEntity.builder().isbn("ISBN").title("To victory").authorEntity(authorEntity).build();
    }

    public static BookDto createTestBookDtoA(final AuthorDto authorDto) {
        return BookDto.builder().isbn("ISBN Book DTO").title("Pursuit of Happiness").authorDto(authorDto).build();
    }
}
