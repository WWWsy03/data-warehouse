package com.ssw331.warehousebackend.controller;

import com.ssw331.warehousebackend.MySQLDTO.*;
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

@Tag(name = "按照上述条件的组合查询和统计")
@RestController
@RequestMapping("/compose")
public class ComposeController {

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
    @GetMapping("/year-type")
    public Result<Object> countMoviesByYearAndType(@RequestParam int year, @RequestParam String type) {
        List<Long> modelTimes = new ArrayList<>();
        List<String> modelLogs = new ArrayList<>();
        long startTime1 = System.currentTimeMillis();
        int count = movieService.countMoviesByYearAndType(year, type);
        modelTimes.add(System.currentTimeMillis() - startTime1);
        modelLogs.add("SELECT COUNT(*) " +
                "FROM Movie m " +
                "JOIN ReleasedIn r ON m.movie_id = r.movie_id " +
                "JOIN Time t ON r.time_id = t.time_id " +
                "WHERE t.year = " + year + " AND m.Type LIKE CONCAT('%', '" + type + "', '%');");



        //hive待写
        long startTime2 = System.currentTimeMillis();
        count = hiveMovieService.countMoviesByYearAndType(year, type);
        modelTimes.add(System.currentTimeMillis() - startTime2);
        modelLogs.add("SELECT COUNT(*) " +
                "FROM Movie m " +
                "JOIN ReleasedIn r ON m.movie_id = r.movie_id " +
                "JOIN Time t ON r.time_id = t.time_id " +
                "WHERE t.year = " + year + " AND m.Type LIKE CONCAT('%', '" + type + "', '%')");

        long startTime3 = System.currentTimeMillis();
        int data = neo4jService.searchMoviesByYearType(year, type);
        modelTimes.add(System.currentTimeMillis() - startTime3);
        modelLogs.add("MATCH (m:Movie)-[r:INCLUDE]->(p:Product) WHERE m.type contains "+type+" RETURN p;");
        return ResultResponse.success(data, modelTimes, modelLogs);
    }

    @GetMapping("/year-director")
    public Result<Object> countMoviesByYearAndDirector(@RequestParam int year, @RequestParam String director_name) {
        List<Long> modelTimes = new ArrayList<>();
        List<String> modelLogs = new ArrayList<>();
        long startTime1 = System.currentTimeMillis();
        int count = movieService.countMoviesByYearAndDirectorName(year,director_name);
        modelTimes.add(System.currentTimeMillis() - startTime1);
        modelLogs.add("SELECT COUNT(*) " +
                "FROM Movie m " +
                "JOIN Time t ON m.release_time_id = t.release_time_id " +
                "WHERE t.year = :year AND m.Type LIKE CONCAT('%', :type, '%')");


        //hive待写
        long startTime2 = System.currentTimeMillis();

        count = hiveMovieService.countMoviesByYearAndDirectorName(year,director_name);
        modelTimes.add(System.currentTimeMillis() - startTime2);
        modelLogs.add("SELECT COUNT(*) " +
                "FROM Movie m " +
                "JOIN Time t ON m.release_time_id = t.release_time_id " +
                "WHERE t.year = " + year + " AND m.Type LIKE CONCAT('%', '" + director_name + "', '%')");

        long startTime3 = System.currentTimeMillis();
        int data = neo4jService.searchMoviesByYearDirector(year, director_name);
        modelTimes.add(System.currentTimeMillis() - startTime3);
        modelLogs.add("MATCH (m:Movie)<-[r:DIRECTED]-(d:Director) WHERE m.release_time contains $year and d.director_name = $directorName return count(m);");
        return ResultResponse.success(data, modelTimes, modelLogs);
    }



}
