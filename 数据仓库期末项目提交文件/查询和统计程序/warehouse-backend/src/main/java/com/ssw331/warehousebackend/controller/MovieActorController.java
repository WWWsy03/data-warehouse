package com.ssw331.warehousebackend.controller;

import com.ssw331.warehousebackend.Neo4jDTO.serialization.Result;
import com.ssw331.warehousebackend.Neo4jDTO.serialization.ResultResponse;
import com.ssw331.warehousebackend.hiveService.HiveMovieActorService;
import com.ssw331.warehousebackend.service.MovieActorService;
import com.ssw331.warehousebackend.service.MovieDirectorService;
import com.ssw331.warehousebackend.service.Neo4jService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "按照演员名称查询参演的电影")
@RestController
@RequestMapping("/actor")
public class MovieActorController {

    @Autowired
    private MovieActorService movieActorService;
    @Autowired
    private HiveMovieActorService hiveMovieActorService;
    @Autowired
    private Neo4jService neo4jService;

    @Autowired
    private void setNeo4jService(Neo4jService neo4jService){
        this.neo4jService=neo4jService;
    }
    @Autowired
    private void setMovieActorService(MovieActorService movieActorService){
        this.movieActorService=movieActorService;
    }
    @Autowired
    private void setMovieDirectorService(MovieActorService movieActorService){
        this.movieActorService=movieActorService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result<Object> movieDirector(@RequestParam String actorName) {
        List<Long> modelTimes = new ArrayList<>();
        List<String> modelLogs = new ArrayList<>();
        long startTime1 = System.currentTimeMillis();
        List<String> dataFromMySQL= movieActorService.getMovieNamesByActorName(actorName);
        modelTimes.add(System.currentTimeMillis() - startTime1);
        modelLogs.add("SELECT m.movie_name " +
                "FROM MovieActor md " +
                "JOIN Movie m ON md.movie_id = m.movie_id " +
                "WHERE md.actor_name = "+actorName+" ; ");

        //hive待写
        long startTime2 = System.currentTimeMillis();
        dataFromMySQL = hiveMovieActorService.getMovieNamesByActorName(actorName);
        modelTimes.add(System.currentTimeMillis() - startTime2);
        modelLogs.add("SELECT m.movie_name " +
                "FROM MovieActor md " +
                "JOIN Movie m ON md.movie_id = m.movie_id " +
                "WHERE md.actor_name = "+actorName+" ; ");

        long startTime3 = System.currentTimeMillis();
        List<String> data = neo4jService.searchMoviesByActor(actorName);
        modelTimes.add(System.currentTimeMillis() - startTime3);
        modelLogs.add("MATCH (m:Movie)<-[r:DIRECTED]-(a:Actor) WHERE a.actor_name = "+actorName+" RETURN m.movie_name;");
        return ResultResponse.success(data, modelTimes, modelLogs);
    }
}