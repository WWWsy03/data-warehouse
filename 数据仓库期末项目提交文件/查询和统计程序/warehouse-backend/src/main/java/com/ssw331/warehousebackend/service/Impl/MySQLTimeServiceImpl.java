package com.ssw331.warehousebackend.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.warehousebackend.MySQLDTO.*;
import com.ssw331.warehousebackend.mapper.*;
import com.ssw331.warehousebackend.service.MySQLTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
@Component
public class MySQLTimeServiceImpl implements MySQLTimeService {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private TimeMapper timeMapper;

    @Override
    public int getMovieCountByYear(int year) {
        QueryWrapper<Time> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("year", year);
        List<Time> times = timeMapper.selectList(queryWrapper);
        int total = 0;
        for (Time time : times) {
            QueryWrapper<Movie> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("release_time_id", time.getReleaseTimeId());
            total += movieMapper.selectCount(queryWrapper2);
        }
        return total;
    }

    @Override
    public int getMovieCountByYearAndMonth(int year, int month) {
        QueryWrapper<Time> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("year", year);
        queryWrapper.eq("month", month);
        List<Time> times = timeMapper.selectList(queryWrapper);

        int total = 0;
        for (Time time : times) {
            QueryWrapper<Movie> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("release_time_id", time.getReleaseTimeId());
            total += movieMapper.selectCount(queryWrapper2);
        }
        return total;
    }

    @Override
    public int getMovieCountByYearAndMonthAndDay(int year, int month, int day) {
        QueryWrapper<Time> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("year", year);
        queryWrapper.eq("month", month);
        queryWrapper.eq("day", month);
        List<Time> times = timeMapper.selectList(queryWrapper);

        int total = 0;
        for (Time time : times) {
            QueryWrapper<Movie> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("release_time_id", time.getReleaseTimeId());
            total += movieMapper.selectCount(queryWrapper2);
        }
        return total;
    }


    @Override
    public int getMovieCountByQuarter(int year,String quarter) {
        int season;
        switch (quarter) {
            case "Q1":
                season = 1;
                break;
            case "Q2":
                season = 2;
                break;
            case "Q3":
                season = 3;
                break;
            case "Q4":
                season = 4;
                break;
            default:
                throw new IllegalArgumentException("Invalid quarter: " + quarter);
        }

        QueryWrapper<Time> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("season", season);
        queryWrapper.eq("year", year);
        List<Time> times = timeMapper.selectList(queryWrapper);

        int total = 0;
        for (Time time : times) {
            QueryWrapper<Movie> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("release_time_id", time.getReleaseTimeId());
            total += movieMapper.selectCount(queryWrapper2);
        }
        return total;
    }


}
