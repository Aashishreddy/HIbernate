package com.example.hibernate.services.impl;

import com.example.hibernate.domain.entities.AuthorEntity;
import com.example.hibernate.repository.AuthorRepository;
import com.example.hibernate.services.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> findAll() {

        // authorRepository.findAll() -> return Iterable<AuthorEntity>
        return StreamSupport.stream(authorRepository
                .findAll()
                .spliterator(),
                false)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Page<AuthorEntity> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Optional<AuthorEntity> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public AuthorEntity updateAuthor(Long id, AuthorEntity authorEntity) {
        Optional<AuthorEntity> author = authorRepository.findById(id);
        if(author.isPresent()) {
            AuthorEntity updatedAuthor = author.get();
            updatedAuthor.setName(authorEntity.getName());
            updatedAuthor.setAge(authorEntity.getAge());
            authorRepository.save(updatedAuthor);
            return updatedAuthor;
        }
        return null;
    }

    @Override
    public boolean ifExists(Long id) {
        Optional<AuthorEntity> author = authorRepository.findById(id);
        if(author.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity) {
        authorEntity.setId(id);
        // Functional and more precise way
//        return authorRepository.findById(id).map(existingAuthor -> {
//            Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
//            Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
//            return authorRepository.save(existingAuthor);
//        }).orElseThrow(() -> new RuntimeException("Author not found"));

        // Normal way
        authorEntity.setId(id);
        Optional<AuthorEntity> author = authorRepository.findById(id);
        if(author.isPresent()) {
            AuthorEntity updatedAuthor = author.get();
            updatedAuthor.setName(authorEntity.getName());
            updatedAuthor.setAge(authorEntity.getAge());
            return authorRepository.save(updatedAuthor);
        } else {
            throw new RuntimeException("Author does not exist");
        }
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
        System.out.println("Deleted Author id: "+ id);
    }
}
