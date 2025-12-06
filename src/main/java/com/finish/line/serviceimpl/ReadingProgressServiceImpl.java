package com.finish.line.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.finish.line.dto.ReadingProgressRequest;
import com.finish.line.dto.ReadingStatsResponse;
import com.finish.line.exception.BookNotFoundException;
import com.finish.line.exception.InvalidReadingInputException;
import com.finish.line.model.Book;
import com.finish.line.model.ReadingSession;
import com.finish.line.repository.BookRepository;
import com.finish.line.repository.ReadingSessionRepository;
import com.finish.line.service.ReadingProgressService;

@Service
public class ReadingProgressServiceImpl implements ReadingProgressService {

    private final BookRepository bookRepository;
    private final ReadingSessionRepository readingSessionRepository;

    public ReadingProgressServiceImpl(BookRepository bookRepository,
            ReadingSessionRepository readingSessionRepository) {
        this.bookRepository = bookRepository;
        this.readingSessionRepository = readingSessionRepository;
    }

    @Override
    public ReadingSession logProgress(ReadingProgressRequest request) {
        if (request.getPagesRead() == null || request.getPagesRead() <= 0) {
            throw new InvalidReadingInputException("pagesRead must be greater than 0");
        }

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new BookNotFoundException(request.getBookId()));

        ReadingSession session = new ReadingSession();
        session.setBookId(book.getId());
        session.setPagesRead(request.getPagesRead());
        session.setTimestamp(LocalDateTime.now());

        return readingSessionRepository.save(session);
    }

    @Override
    public ReadingStatsResponse getHistory(String bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        List<ReadingSession> sessions = readingSessionRepository
                .findByBookIdOrderByTimestampAsc(bookId);

        List<ReadingStatsResponse.SessionDto> sessionDtos = sessions.stream()
                .map(s -> new ReadingStatsResponse.SessionDto(
                s.getSessionId(),
                s.getPagesRead(),
                s.getTimestamp()
        ))
                .collect(Collectors.toList());

        ReadingStatsResponse response = new ReadingStatsResponse();
        response.setBookId(book.getId());
        response.setTitle(book.getTitle());
        response.setSessions(sessionDtos);
        return response;
    }

    @Override
    public void resetProgress(String bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        readingSessionRepository.deleteByBookId(book.getId());
    }
}
