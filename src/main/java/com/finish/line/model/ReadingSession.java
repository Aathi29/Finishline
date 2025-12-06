package com.finish.line.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "reading_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadingSession {

    @Id
    private String sessionId;
    private String bookId;
    private Integer pagesRead;
    private LocalDateTime timestamp;
}
