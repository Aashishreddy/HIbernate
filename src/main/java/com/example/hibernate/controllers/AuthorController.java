package com.example.hibernate.controllers;

import com.example.hibernate.domain.entities.AuthorEntity;
import com.example.hibernate.domain.dto.AuthorDto;
import com.example.hibernate.mappers.impl.AuthorMapperImpl;
import com.example.hibernate.services.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;

    private AuthorMapperImpl authorMapper;

    public AuthorController(AuthorService authorService, AuthorMapperImpl authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping(value = "/save", consumes = "application/json")
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto author) {

        // Seperating Presentation layer and persistence layer.
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity); // OK for Service layer to have knowledge about Entities.
        AuthorDto authorDto = authorMapper.mapTo(savedAuthorEntity);
        return new ResponseEntity<>(authorDto, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {

        List<AuthorEntity> authorList = authorService.findAll();
        return new ResponseEntity<>(
                authorList.stream()
                        .map(authorMapper::mapTo)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/getPagingAuthors")
    public Page<AuthorDto> getAuthors(Pageable pageable) {
        Page<AuthorEntity> authors = authorService.findAll(pageable);
        return authors.map(authorMapper::mapTo);
    }

    /**
     * findById
     */
    @GetMapping("/findAuthor/{id}")
    public ResponseEntity<AuthorDto> findById(@PathVariable("id") Long id) {
        Optional<AuthorEntity> authorResult = authorService.findById(id);
        AuthorDto authorDto = null;
        if(authorResult.isPresent()) {
            AuthorEntity authorEntityR = authorResult.get();
            authorDto = authorMapper.mapTo(authorEntityR);
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

//        return authorResult.map(
//                AuthorEntity -> {
//                    AuthorDto authorDto1 = authorMapper.mapTo(AuthorEntity);
//                    return new ResponseEntity<>(authorDto1, HttpStatus.OK);
//                }
//        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

//
    /**
     * Update
     */
    @PutMapping("/updateAuthor/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {

        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity updatedAuthor = authorService.updateAuthor(id, authorEntity);
        AuthorDto updatedAuthorDto = authorMapper.mapTo(updatedAuthor);

        if (updatedAuthorDto.getId() != null){
            return new ResponseEntity<>(updatedAuthorDto, HttpStatus.OK);
        } else {
            //not going here
            //add method !ifExists in authorService
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Partial Update
     */
    @PatchMapping("/partialUpdateAuthor/{id}")
    public ResponseEntity<AuthorDto> partialUpdate(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
        if (authorService.ifExists(id) == false) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
            AuthorEntity author1 = authorService.partialUpdate(id, authorEntity);
            AuthorDto authorDto1 = authorMapper.mapTo(author1);
            return new ResponseEntity<>(authorDto1, HttpStatus.OK);
        }
    }


    /**
     * delete
     */
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * TODO: Write Integration tests
     */

}
