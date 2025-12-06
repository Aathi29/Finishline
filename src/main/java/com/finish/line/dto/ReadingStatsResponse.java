package com.finish.line.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ReadingStatsResponse {

    private String bookId;
    private String title;
    private List<SessionDto> sessions;

    public ReadingStatsResponse(String bookId, List<SessionDto> sessions, String title) {
        this.bookId = bookId;
        this.sessions = sessions;
        this.title = title;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SessionDto> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionDto> sessions) {
        this.sessions = sessions;
    }

    public static class SessionDto {

        private String sessionId;
        private Integer pagesRead;
        private LocalDateTime timestamp;

        public SessionDto(String sessionId, Integer pagesRead, LocalDateTime timestamp) {
            this.sessionId = sessionId;
            this.pagesRead = pagesRead;
            this.timestamp = timestamp;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public Integer getPagesRead() {
            return pagesRead;
        }

        public void setPagesRead(Integer pagesRead) {
            this.pagesRead = pagesRead;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

    }
}
