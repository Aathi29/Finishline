package com.finish.line.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadingForecastResponse {

    private String bookId;
    private Integer totalPages;
    private Integer pagesReadSoFar;
    private Double averagePagesPerDay;
    private String readingPattern;
    private Long projectedDaysToFinish;
    private LocalDate estimatedFinishDate;
    private String confidence;

    public ReadingForecastResponse(Double averagePagesPerDay, String bookId, String confidence, LocalDate estimatedFinishDate, Integer pagesReadSoFar, Long projectedDaysToFinish, String readingPattern, Integer totalPages) {
        this.averagePagesPerDay = averagePagesPerDay;
        this.bookId = bookId;
        this.confidence = confidence;
        this.estimatedFinishDate = estimatedFinishDate;
        this.pagesReadSoFar = pagesReadSoFar;
        this.projectedDaysToFinish = projectedDaysToFinish;
        this.readingPattern = readingPattern;
        this.totalPages = totalPages;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPagesReadSoFar() {
        return pagesReadSoFar;
    }

    public void setPagesReadSoFar(Integer pagesReadSoFar) {
        this.pagesReadSoFar = pagesReadSoFar;
    }

    public Double getAveragePagesPerDay() {
        return averagePagesPerDay;
    }

    public void setAveragePagesPerDay(Double averagePagesPerDay) {
        this.averagePagesPerDay = averagePagesPerDay;
    }

    public String getReadingPattern() {
        return readingPattern;
    }

    public void setReadingPattern(String readingPattern) {
        this.readingPattern = readingPattern;
    }

    public Long getProjectedDaysToFinish() {
        return projectedDaysToFinish;
    }

    public void setProjectedDaysToFinish(Long projectedDaysToFinish) {
        this.projectedDaysToFinish = projectedDaysToFinish;
    }

    public LocalDate getEstimatedFinishDate() {
        return estimatedFinishDate;
    }

    public void setEstimatedFinishDate(LocalDate estimatedFinishDate) {
        this.estimatedFinishDate = estimatedFinishDate;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

}
