package com.example.hibernate.repository;

import com.example.hibernate.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends CrudRepository<BookEntity, String> {
}
