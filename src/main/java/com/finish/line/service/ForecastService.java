package com.finish.line.service;

import com.finish.line.dto.ReadingForecastResponse;

public interface ForecastService {

    ReadingForecastResponse getForecast(String bookId);

}
