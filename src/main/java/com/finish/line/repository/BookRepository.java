package com.finish.line.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finish.line.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
