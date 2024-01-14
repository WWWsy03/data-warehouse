package com.ssw331.warehousebackend.controller;

import com.ssw331.warehousebackend.MySQLDTO.*;
import com.ssw331.warehousebackend.Neo4jDTO.Movie;
import com.ssw331.warehousebackend.Neo4jDTO.serialization.Result;
import com.ssw331.warehousebackend.Neo4jDTO.serialization.ResultResponse;
import com.ssw331.warehousebackend.hiveService.HiveMovieService;
import com.ssw331.warehousebackend.service.Impl.Neo4jServiceImpl;
import com.ssw331.warehousebackend.service.*;

import com.ssw331.warehousebackend.service.Neo4jService;
import com.ssw331.warehousebackend.service.ProductByNameService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "按照电影类别进行查询及统计")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private  Neo4jService neo4jService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private HiveMovieService hiveMovieService;

    @Autowired
    private void setNeo4jService(Neo4jService neo4jService){
        this.neo4jService=neo4jService;
    }


    @GetMapping("/get-movies")
    public Result<Object> getMoviesByType(@RequestParam String type){
        List<Long> modelTimes = new ArrayList<>();
        List<String> modelLogs = new ArrayList<>();
        long startTime1 = System.currentTimeMillis();
        List<String> movieNames = movieService.getMoviesByType(type);
        modelTimes.add(System.currentTimeMillis() - startTime1);
        modelLogs.add("SELECT m.movie_name " +
                "FROM Movie m " +
                "WHERE m.Type LIKE CONCAT('%', :type, '%')");


        //hive待写
        long startTime2 = System.currentTimeMillis();
        movieNames = hiveMovieService.getMoviesByType(type);
        modelTimes.add(System.currentTimeMillis() - startTime2);
        modelLogs.add("SELECT m.movie_name " +
                "FROM Movie m " +
                "WHERE m.Type LIKE CONCAT('%', :type, '%')");

        long startTime3 = System.currentTimeMillis();
        List<String> data = neo4jService.searchMoviesByCategory(type);
        modelTimes.add(System.currentTimeMillis() - startTime3);
        modelLogs.add("MATCH (m:Movie)-[r:INCLUDE]->(p:Product) " +
                "WHERE m.movie_name CONTAINS $type " +
                "RETURN p");
        return ResultResponse.success(data, modelTimes, modelLogs);
    }



}
