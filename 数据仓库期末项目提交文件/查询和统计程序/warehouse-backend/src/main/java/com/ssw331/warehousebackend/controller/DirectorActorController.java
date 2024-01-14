package com.ssw331.warehousebackend.controller;

import com.ssw331.warehousebackend.Neo4jDTO.serialization.Result;
import com.ssw331.warehousebackend.Neo4jDTO.serialization.ResultResponse;
import com.ssw331.warehousebackend.hiveService.HiveDirectorActorService;
import com.ssw331.warehousebackend.service.DirectorActorService;
import com.ssw331.warehousebackend.service.MySQLTimeService;
import com.ssw331.warehousebackend.service.Neo4jService;
import com.ssw331.warehousebackend.service.TimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "按照演员和导演的关系进行查询及统计")
@RestController
@RequestMapping("/director-actor")
public class DirectorActorController {
    @Autowired
    DirectorActorService directorActorService;
    @Autowired
    Neo4jService neo4jService;
    @Autowired
    HiveDirectorActorService hiveDirectorActorService;


    @Autowired
    private void setNeo4jService(Neo4jService neo4jService) {
        this.neo4jService = neo4jService;
    }

    @Autowired
    private void setDirectorActorService(DirectorActorService directorActorService) {
        this.directorActorService = directorActorService;
    }
    @Operation(summary = "输入导演名称得到与其合作的演员名称")
    @RequestMapping(value = "/actor-by-director", method = RequestMethod.GET)
    public Result<Object> actorByDirector(@RequestParam String directorName) {
        List<Long> modelTimes = new ArrayList<>();
        List<String> modelLogs = new ArrayList<>();
        long startTime1 = System.currentTimeMillis();
        List<String> dataFromMySQL=directorActorService.getActorNamesByDirectorName(directorName);
        modelTimes.add(System.currentTimeMillis() - startTime1);
        modelLogs.add("SELECT sda.actor_name2 " +
                "FROM StaticDirectorActor sda " +
                "WHERE sda.actor_name1 = "+directorName+";");

        //hive
        long startTime2 = System.currentTimeMillis();
        dataFromMySQL = hiveDirectorActorService.getActorNamesByDirectorName(directorName);
        modelTimes.add(System.currentTimeMillis() - startTime2);
        modelLogs.add("SELECT sda.actor_name2 " +
                "FROM StaticDirectorActor sda " +
                "WHERE sda.actor_name1 = " + directorName + ";");
        long startTime3 = System.currentTimeMillis();
        List<String> data = neo4jService.searchActorByDirector(directorName);
        modelTimes.add(System.currentTimeMillis() - startTime3);
        modelLogs.add("MATCH (a:Actor)-[:ACTED_IN]->(m)<-[:DIRECTED]-(d:Director) WHERE d.director_name = "+directorName+" RETURN a.actor_name;");
        return ResultResponse.success(data, modelTimes, modelLogs);
    }

    @Operation(summary = "输入导演名称得到与其合作的导演名称")
    @RequestMapping(value = "/director-by-director", method = RequestMethod.GET)
    public Result<Object> directorByDirector(@RequestParam String directorName) {
        List<Long> modelTimes = new ArrayList<>();
        List<String> modelLogs = new ArrayList<>();
        long startTime1 = System.currentTimeMillis();
        List<String> dataFromMySQL=directorActorService.getDirectorNamesByDirectorName(directorName);
        modelTimes.add(System.currentTimeMillis() - startTime1);
        modelLogs.add("SELECT sdd.director_name2"+
                "FROM StaticDirectorDirector sdd"+
                "WHERE sdd.director_name1 = "+directorName+";");
        long startTime2 = System.currentTimeMillis();
        dataFromMySQL = hiveDirectorActorService.getDirectorNamesByDirectorName(directorName);
        modelTimes.add(System.currentTimeMillis() - startTime2);
        modelLogs.add("SELECT sdd.director_name2" +
                "FROM StaticDirectorDirector sdd" +
                "WHERE sdd.director_name1 = " + directorName + ";");
        long startTime3 = System.currentTimeMillis();
        List<String> data = neo4jService.searchDirectorByDirector(directorName);
        modelTimes.add(System.currentTimeMillis() - startTime3);
        modelLogs.add("MATCH (d1:Director)-[:COOPERATE]->(d2:Director) WHERE d1.director_name = "+directorName+" RETURN d2.director_name;");
        return ResultResponse.success(data, modelTimes, modelLogs);
    }

    @Operation(summary = "输入演员名称得到与其合作的演员名称")
    @RequestMapping(value = "/actor-by-actor", method = RequestMethod.GET)
    public Result<Object> actorByActor(@RequestParam String actorName) {
        List<Long> modelTimes = new ArrayList<>();
        List<String> modelLogs = new ArrayList<>();
        long startTime1 = System.currentTimeMillis();
        List<String> dataFromMySQL=directorActorService.getActorNamesByActorName(actorName);
        modelTimes.add(System.currentTimeMillis() - startTime1);
        modelLogs.add("SELECT saa.actor_name2 " +
                "FROM StaticActorActor saa " +
                "WHERE saa.actor_name1 = "+actorName+";");
        long startTime2 = System.currentTimeMillis();
        dataFromMySQL = directorActorService.getActorNamesByActorName(actorName);
        modelTimes.add(System.currentTimeMillis() - startTime2);
        modelLogs.add("SELECT saa.actor_name2 " +
                "FROM StaticActorActor saa " +
                "WHERE saa.actor_name1 = " + actorName + ";");
        long startTime3 = System.currentTimeMillis();
        List<String> data = neo4jService.searchActorByActor(actorName);
        modelTimes.add(System.currentTimeMillis() - startTime3);
        modelLogs.add("MATCH (a1:Actor)-[r:COOPERATE]->(a2:Actor) WHERE a1.actor_name = "+actorName+" RETURN a2.actor_name;");
        return ResultResponse.success(data, modelTimes, modelLogs);
    }

    @Operation(summary = "输入演员名称得到与其合作的导演名称")
    @RequestMapping(value = "/director-by-actor", method = RequestMethod.GET)
    public Result<Object> directorByActor(@RequestParam String actorName) {
        List<Long> modelTimes = new ArrayList<>();
        List<String> modelLogs = new ArrayList<>();
        long startTime1 = System.currentTimeMillis();
        List<String> dataFromMySQL = directorActorService.getDirectorNamesByActorName(actorName);
        modelTimes.add(System.currentTimeMillis() - startTime1);
        modelLogs.add("SELECT sda.director_name2 " +
                "FROM StaticDirectorActor sda " +
                "WHERE sda.actor_name1 = " + actorName + ";");
        long startTime2 = System.currentTimeMillis();
        dataFromMySQL = hiveDirectorActorService.getDirectorNamesByActorName(actorName);
        modelTimes.add(System.currentTimeMillis() - startTime2);
        modelLogs.add("SELECT sda.director_name2 " +
                "FROM StaticDirectorActor sda " +
                "WHERE sda.actor_name1 = " + actorName + ";");
        long startTime3 = System.currentTimeMillis();
        List<String> data = neo4jService.searchDirectorByActor(actorName);
        modelTimes.add(System.currentTimeMillis() - startTime3);
        modelLogs.add("MATCH (a:Director)-[:DIRECTED]->(m)<-[:ACTED_IN]-(d:Actor) WHERE d.actor_name = "+actorName+" RETURN a.director_name;");
        return ResultResponse.success(data, modelTimes, modelLogs);
    }
}
