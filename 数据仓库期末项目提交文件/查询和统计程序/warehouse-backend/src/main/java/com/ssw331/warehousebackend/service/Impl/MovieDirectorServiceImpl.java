package com.ssw331.warehousebackend.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.warehousebackend.MySQLDTO.Movie;
import com.ssw331.warehousebackend.MySQLDTO.MovieDirector;
import com.ssw331.warehousebackend.mapper.MovieDirectorMapper;
import com.ssw331.warehousebackend.mapper.MovieMapper;
import com.ssw331.warehousebackend.service.MovieDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDirectorServiceImpl implements MovieDirectorService {
    @Autowired
    private MovieDirectorMapper movieDirectorMapper;
    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<String> getMovieNamesByDirectorName(String directorName) {
        QueryWrapper<MovieDirector> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("director_name", directorName);
        List<MovieDirector> movieDirectors = movieDirectorMapper.selectList(queryWrapper);

        List<String> movieNames = new ArrayList<>();
        for (MovieDirector movieDirector : movieDirectors) {
            Movie movie = movieMapper.selectById(movieDirector.getMovieId());
            movieNames.add(movie.getMovieName());
        }
        return movieNames;
    }

}
