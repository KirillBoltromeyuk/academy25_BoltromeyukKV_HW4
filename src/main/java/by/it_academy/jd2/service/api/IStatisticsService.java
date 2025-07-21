package by.it_academy.jd2.service.api;

import jakarta.servlet.ServletContext;

import java.util.Map;

public interface IStatisticsService {
    Map<String, Integer> getStatistics(ServletContext context);
}