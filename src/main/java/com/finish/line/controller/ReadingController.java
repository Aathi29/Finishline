package com.finish.line.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finish.line.dto.ReadingForecastResponse;
import com.finish.line.dto.ReadingProgressRequest;
import com.finish.line.dto.ReadingStatsResponse;
import com.finish.line.model.ReadingSession;
import com.finish.line.service.ForecastService;
import com.finish.line.service.ReadingProgressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reading")
public class ReadingController {

    private final ForecastService forecastService;
    private final ReadingProgressService readingProgressService;

    public ReadingController(ForecastService forecastService, ReadingProgressService readingProgressService) {
        this.forecastService = forecastService;
        this.readingProgressService = readingProgressService;
    }

    @PostMapping("/progress")
    public ResponseEntity<?> logProgress(@Valid @RequestBody ReadingProgressRequest request) {
        ReadingSession session = readingProgressService.logProgress(request);
        return ResponseEntity.ok(
                Map.of(
                        "message", "Reading progress saved successfully",
                        "bookId", session.getBookId(),
                        "pagesRead", session.getPagesRead(),
                        "sessionId", session.getSessionId(),
                        "timestamp", session.getTimestamp()
                )
        );
    }

    @GetMapping("/forecast")
    public ResponseEntity<ReadingForecastResponse> getForecast(@RequestParam String bookId) {
        return ResponseEntity.ok(forecastService.getForecast(bookId));
    }

    @GetMapping("/{bookId}/history")
    public ResponseEntity<ReadingStatsResponse> getHistory(@PathVariable String bookId) {
        return ResponseEntity.ok(readingProgressService.getHistory(bookId));
    }

    @DeleteMapping("/{bookId}/reset")
    public ResponseEntity<?> reset(@PathVariable String bookId) {
        readingProgressService.resetProgress(bookId);
        return ResponseEntity.ok(
                Map.of(
                        "bookId", bookId,
                        "message", "All reading progress has been reset",
                        "timestamp", LocalDateTime.now()
                )
        );
    }
}
