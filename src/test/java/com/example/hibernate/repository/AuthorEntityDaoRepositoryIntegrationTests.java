package com.example.hibernate.repository;

import com.example.hibernate.TestDataUtil;
import com.example.hibernate.domain.entities.AuthorEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorEntityDaoRepositoryIntegrationTests {

    private AuthorRepository authorRepository;

    public AuthorEntity a1;
    public AuthorEntity a2;
    public AuthorEntity a3;

    @Autowired
    public AuthorEntityDaoRepositoryIntegrationTests(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Test
    public void createAuthorUsingHibernate() {

        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        authorRepository.save(authorEntity);
        Optional<AuthorEntity> result = authorRepository.findById(authorEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntity);
    }

    @Test
    public void createTestAuthors() {
        a1 = TestDataUtil.createTestAuthor();
        authorRepository.save(a1);
        a2 = TestDataUtil.createTestAuthorB();
        authorRepository.save(a2);
        a3 = TestDataUtil.createTestAuthorC();
        authorRepository.save(a3);
    }

    @Test
    public void getAuthorsLessThan50() {

        a1 = TestDataUtil.createTestAuthor();
        authorRepository.save(a1);
        a2 = TestDataUtil.createTestAuthorB();
        authorRepository.save(a2);
        a3 = TestDataUtil.createTestAuthorC();
        authorRepository.save(a3);

        Iterable<AuthorEntity> authors = authorRepository.ageLessThan(50);
        assertThat(authors).hasSize(2).containsExactly(a2, a3);
    }

    @BeforeEach
    public void setup() {
        authorRepository.deleteAll();
    }

    @Test
    public void findAuthorsWithAgeGreaterThan50() {
        a1 = TestDataUtil.createTestAuthor();
        authorRepository.save(a1);
        a2 = TestDataUtil.createTestAuthorB();
        authorRepository.save(a2);
        a3 = TestDataUtil.createTestAuthorC();
        authorRepository.save(a3);

        Iterable<AuthorEntity> authors = authorRepository.findAuthorsWithAgeGreaterThan(50);
        assertThat(authors).hasSize(1);
        assertThat(authors.iterator().next()).isEqualTo(a1);
    }

    @AfterEach
    public void tearDown() {
        authorRepository.deleteAll();
    }


    /***
     * Write Integration Tests for Update, finaAll, Delete
     */


}
