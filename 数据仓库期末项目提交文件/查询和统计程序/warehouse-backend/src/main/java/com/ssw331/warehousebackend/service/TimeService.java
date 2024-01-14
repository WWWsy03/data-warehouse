package com.ssw331.warehousebackend.service;

public interface TimeService {
    int searchMoviesByYear(int year);
    int searchMoviesByYM(int year, int month);
    int searchMoviesByYMD(int year, int month, int day);
    int searchMoviesByYS(int year, String season);
}
