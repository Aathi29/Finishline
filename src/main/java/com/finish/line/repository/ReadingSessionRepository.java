package com.finish.line.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finish.line.model.ReadingSession;

public interface ReadingSessionRepository extends MongoRepository<ReadingSession, String> {

    List<ReadingSession> findByBookIdOrderByTimestampAsc(String bookId);

    void deleteByBookId(String bookId);
}
