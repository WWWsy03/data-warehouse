package com.ssw331.warehousebackend.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.warehousebackend.MySQLDTO.Movie;
import com.ssw331.warehousebackend.MySQLDTO.MovieActor;
import com.ssw331.warehousebackend.mapper.MovieActorMapper;
import com.ssw331.warehousebackend.mapper.MovieMapper;
import com.ssw331.warehousebackend.service.MovieActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieActorServiceImpl implements MovieActorService {
    @Autowired
    private MovieActorMapper movieActorMapper;
    @Autowired
    private MovieMapper movieMapper;

@Override
    public List<String> getMovieNamesByActorName(String actorName) {
        QueryWrapper<MovieActor> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("actor_name", actorName);
        List<MovieActor> movieActors = movieActorMapper.selectList(queryWrapper);

        List<String> movieNames = new ArrayList<>();
        for (MovieActor movieActor : movieActors) {
            Movie movie = movieMapper.selectById(movieActor.getMovieId());
            movieNames.add(movie.getMovieName());
        }
        return movieNames;
    }
}
