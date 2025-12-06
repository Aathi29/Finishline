package com.finish.line.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.finish.line.dto.ReadingForecastResponse;
import com.finish.line.exception.BookNotFoundException;
import com.finish.line.model.Book;
import com.finish.line.model.ReadingSession;
import com.finish.line.repository.BookRepository;
import com.finish.line.repository.ReadingSessionRepository;
import com.finish.line.service.ForecastService;
import com.finish.line.util.VelocityCalculator;

@Service
public class ForecastServiceImpl implements ForecastService {

    private final BookRepository bookRepository;
    private final ReadingSessionRepository readingSessionRepository;
    private final VelocityCalculator velocityCalculator;

    public ForecastServiceImpl(BookRepository bookRepository,
            ReadingSessionRepository readingSessionRepository,
            VelocityCalculator velocityCalculator) {
        this.bookRepository = bookRepository;
        this.readingSessionRepository = readingSessionRepository;
        this.velocityCalculator = velocityCalculator;
    }

    @Override
    public ReadingForecastResponse getForecast(String bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        List<ReadingSession> sessions
                = readingSessionRepository.findByBookIdOrderByTimestampAsc(bookId);

        int totalPagesRead = sessions.stream()
                .mapToInt(ReadingSession::getPagesRead)
                .sum();

        double avgPagesPerDay
                = velocityCalculator.calculateAveragePagesPerDay(sessions);

        String pattern
                = velocityCalculator.determineReadingPattern(sessions);

        long projectedDaysToFinish
                = velocityCalculator.estimateDaysToFinish(
                        book.getTotalPages(),
                        totalPagesRead,
                        avgPagesPerDay
                );

        ReadingForecastResponse response = new ReadingForecastResponse();
        response.setBookId(book.getId());
        response.setTotalPages(book.getTotalPages());
        response.setPagesReadSoFar(totalPagesRead);
        response.setAveragePagesPerDay(avgPagesPerDay);
        response.setReadingPattern(pattern);
        response.setProjectedDaysToFinish(projectedDaysToFinish);

        if (projectedDaysToFinish <= 0) {
            response.setEstimatedFinishDate(LocalDate.now());
            response.setConfidence("low");
        } else {
            response.setEstimatedFinishDate(LocalDate.now().plusDays(projectedDaysToFinish));
            response.setConfidence("medium");
        }

        return response;
    }
}
