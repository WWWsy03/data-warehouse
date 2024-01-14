package com.ssw331.warehousebackend.service;

import com.ssw331.warehousebackend.MySQLDTO.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MovieService {
    List<Movie> getMoviesWithHighGradeProducts();
    List<String> getMoviesByType(String type);
    int countMoviesByYearAndType(int year, String type);
    int countMoviesByYearAndDirectorName(int year, String directorName);
}