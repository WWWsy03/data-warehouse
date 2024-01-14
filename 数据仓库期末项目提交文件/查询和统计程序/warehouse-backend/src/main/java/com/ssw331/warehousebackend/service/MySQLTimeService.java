package com.ssw331.warehousebackend.service;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MySQLTimeService {
    int getMovieCountByYear(int year);
    int getMovieCountByYearAndMonth(int year, int month);
    int getMovieCountByYearAndMonthAndDay(int year, int month,int day);
    int getMovieCountByQuarter(int year,String quarter);

}
