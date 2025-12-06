package com.finish.line.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReadingProgressRequest {

    @NotBlank
    private String bookId;

    @NotNull
    @Min(1)
    private Integer pagesRead;

    public ReadingProgressRequest(String bookId, Integer pagesRead) {
        this.bookId = bookId;
        this.pagesRead = pagesRead;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Integer getPagesRead() {
        return pagesRead;
    }

    public void setPagesRead(Integer pagesRead) {
        this.pagesRead = pagesRead;
    }

}
