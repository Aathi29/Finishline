package com.finish.line.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "books")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Book {

    @Id
    private String id;
    private String title;
    private Integer totalPages;

}
