package com.ssw331.warehousebackend.controller;

import com.ssw331.warehousebackend.MySQLDTO.Product;
import com.ssw331.warehousebackend.Neo4jDTO.serialization.Result;
import com.ssw331.warehousebackend.Neo4jDTO.serialization.ResultResponse;
import com.ssw331.warehousebackend.hiveService.HiveMovieDirectorService;
import com.ssw331.warehousebackend.service.MovieDirectorService;
import com.ssw331.warehousebackend.service.Neo4jService;
import com.ssw331.warehousebackend.service.ProductByNameService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "按照导演名称查询导演过的电影")
@RestController
@RequestMapping("/director")
public class MovieDirectorController {

    @Autowired
    private MovieDirectorService movieDirectorService;
    @Autowired
    private HiveMovieDirectorService hiveMovieDirectorService;
    @Autowired
    private Neo4jService neo4jService;

    @Autowired
    private void setNeo4jService(Neo4jService neo4jService){
        this.neo4jService=neo4jService;
    }
    @Autowired
    private void setMovieDirectorService(MovieDirectorService movieDirectorService){
        this.movieDirectorService=movieDirectorService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result<Object> movieDirector(@RequestParam String directorName) {
        List<Long> modelTimes = new ArrayList<>();
        List<String> modelLogs = new ArrayList<>();
        long startTime1 = System.currentTimeMillis();
        List<String> dataFromMySQL= movieDirectorService.getMovieNamesByDirectorName(directorName);
        modelTimes.add(System.currentTimeMillis() - startTime1);
        modelLogs.add("SELECT m.movie_name " +
                "FROM MovieDirector md " +
                "JOIN Movie m ON md.movie_id = m.movie_id " +
                "WHERE md.director_name = "+directorName+" ; ");

        //hive待写
        long startTime2 = System.currentTimeMillis();
        dataFromMySQL = hiveMovieDirectorService.getMovieNamesByDirectorName(directorName);
        modelTimes.add(System.currentTimeMillis() - startTime2);
        modelLogs.add("SELECT m.movie_name " +
                "FROM MovieDirector md " +
                "JOIN Movie m ON md.movie_id = m.movie_id " +
                "WHERE md.director_name = "+directorName+" ; ");

        long startTime3 = System.currentTimeMillis();
        List<String> data = neo4jService.searchMoviesByDirector(directorName);
        modelTimes.add(System.currentTimeMillis() - startTime3);
        modelLogs.add("MATCH (m:Movie)<-[r:DIRECTED]-(a:Director) WHERE a.director_name = "+directorName+" RETURN m.movie_name;");
        return ResultResponse.success(data, modelTimes, modelLogs);
    }
}
