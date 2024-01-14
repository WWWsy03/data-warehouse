package com.ssw331.warehousebackend.service.Impl;

import com.ssw331.warehousebackend.service.TimeService;
import com.ssw331.warehousebackend.service.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimeServiceImpl implements TimeService {


    MovieRepository movieRepository;

    @Autowired
    private void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     *
     * @param year 年份 yyyy
     * @return 这一年内发布的电影
     */
    @Override
    public int searchMoviesByYear(int year) {
        return movieRepository.countMoviesByReleaseTimeContaining(Integer.toString(year));
    }

    /**
     *
     * @param year 年份 yyyy
     * @param month 月份 MM
     * @return 这一个月发布的电影
     */
    @Override
    public int searchMoviesByYM(int year, int month) {
        return movieRepository.countMoviesByReleaseTimeContaining(year + "/" + month);
    }

    /**
     *
     * @param year 年份 yyyy
     * @param month 月份 MM
     * @param day 日期 dd
     * @return 这一天内发布的电影
     */
    @Override
    public int searchMoviesByYMD(int year, int month, int day) {
        return movieRepository.countMoviesByReleaseTimeContaining(year + "/" + month + "/" + day);
    }

    /**
     *
     * @param year 年份 yyyy
     * @param season 季度 QX
     * @return 这一季度发布的电影
     */
    @Override
    public int searchMoviesByYS(int year, String season) {
        int res1 = 0, res2 = 0, res3 = 0;
        switch (season) {
            case "Q1":
                res1 = movieRepository.countMoviesByReleaseTimeContaining(year + "/" + 1);
                res2 = movieRepository.countMoviesByReleaseTimeContaining(year + "/" + 2);
                res3 = movieRepository.countMoviesByReleaseTimeContaining(year + "/" + 3);
                break;
            case "Q2":
                res1 = movieRepository.countMoviesByReleaseTimeContaining(year + "/" + 4);
                res2 = movieRepository.countMoviesByReleaseTimeContaining(year + "/" + 5);
                res3 = movieRepository.countMoviesByReleaseTimeContaining(year + "/" + 6);
                break;
            case "Q3":
                res1 = movieRepository.countMoviesByReleaseTimeContaining(year + "/" + 7);
                res2 = movieRepository.countMoviesByReleaseTimeContaining(year + "/" + 8);
                res3 = movieRepository.countMoviesByReleaseTimeContaining(year + "/" + 9);
                break;
            case "Q4":
                res1 = movieRepository.countMoviesByReleaseTimeContaining(year + "/" + 10);
                res2 = movieRepository.countMoviesByReleaseTimeContaining(year + "/" + 11);
                res3 = movieRepository.countMoviesByReleaseTimeContaining(year + "/" + 12);
                break;
        }
        return res1 + res2 + res3;
    }

}
