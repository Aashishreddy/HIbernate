package com.example.hibernate.repository;

import com.example.hibernate.TestDataUtil;
import com.example.hibernate.domain.entities.AuthorEntity;
import com.example.hibernate.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookEntityDaoRepositoryIntegrationTests {

    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    @Autowired
    public BookEntityDaoRepositoryIntegrationTests(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Test
    public void createBookUsingHibernate() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        authorRepository.save(authorEntity);
        BookEntity bookEntity = TestDataUtil.createTestBookEntityA(authorEntity);
        bookRepository.save(bookEntity);
        Optional<BookEntity> result = bookRepository.findById(bookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);
    }

}
