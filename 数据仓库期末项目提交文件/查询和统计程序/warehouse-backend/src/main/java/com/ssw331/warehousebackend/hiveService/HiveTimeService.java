package com.ssw331.warehousebackend.hiveService;

import org.springframework.stereotype.Service;

@Service
public interface HiveTimeService {
    int getMovieCountByYear(int year);
    int getMovieCountByYearAndMonth(int year, int month);
    int getMovieCountByYearAndMonthAndDay(int year, int month,int day);
    int getMovieCountByQuarter(int year,String quarter);

}
