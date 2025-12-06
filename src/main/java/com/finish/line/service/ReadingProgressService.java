package com.finish.line.service;

import com.finish.line.dto.ReadingProgressRequest;
import com.finish.line.dto.ReadingStatsResponse;
import com.finish.line.model.ReadingSession;

public interface ReadingProgressService {

    ReadingSession logProgress(ReadingProgressRequest request);

    ReadingStatsResponse getHistory(String bookId);

    void resetProgress(String bookId);
}
