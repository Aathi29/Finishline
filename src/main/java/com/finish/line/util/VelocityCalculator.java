package com.finish.line.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Component;

import com.finish.line.model.ReadingSession;

@Component
public class VelocityCalculator {

    public double calculateAveragePagesPerDay(List<ReadingSession> sessions) {
        if (sessions == null || sessions.isEmpty()) {
            return 0.0;
        }

        int totalPages = sessions.stream()
                .mapToInt(ReadingSession::getPagesRead)
                .sum();

        LocalDate first = sessions.get(0).getTimestamp()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate last = sessions.get(sessions.size() - 1).getTimestamp()
                .atZone(ZoneId.systemDefault()).toLocalDate();

        long days = java.time.temporal.ChronoUnit.DAYS.between(first, last) + 1;
        if (days <= 0) {
            days = 1;
        }

        return (double) totalPages / days;
    }

    public String determineReadingPattern(List<ReadingSession> sessions) {
        if (sessions == null || sessions.size() < 3) {
            return "unknown";
        }

        double avg = calculateAveragePagesPerDay(sessions);
        if (avg == 0) {
            return "inactive";
        }

        long highDays = sessions.stream()
                .filter(s -> s.getPagesRead() > avg * 1.5)
                .count();

        double ratio = (double) highDays / sessions.size();
        return ratio > 0.3 ? "burst" : "steady";
    }

    public long estimateDaysToFinish(int totalPages, int pagesReadSoFar, double avgPagesPerDay) {
        int remaining = totalPages - pagesReadSoFar;
        if (remaining <= 0 || avgPagesPerDay <= 0) {
            return 0;
        }
        return (long) Math.ceil(remaining / avgPagesPerDay);
    }
}
