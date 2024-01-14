

package com.ssw331.warehousebackend.controller;

import com.ssw331.warehousebackend.MySQLDTO.*;
import com.ssw331.warehousebackend.Neo4jDTO.ReviewMax_AA;
import com.ssw331.warehousebackend.Neo4jDTO.serialization.Result;
import com.ssw331.warehousebackend.Neo4jDTO.serialization.ResultResponse;
import com.ssw331.warehousebackend.hiveService.HiveRelationService;
import com.ssw331.warehousebackend.service.Neo4jService;
import com.ssw331.warehousebackend.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.yahoo.sketches.quantiles.ItemsSketch.rand;

@RestController
@RequestMapping("/relation")
public class RelationController {

    @Autowired
    private RelationService relationService;
    @Autowired
    private HiveRelationService hiveRelationService;
    @Autowired
    private Neo4jService neo4jService;

    @Autowired
    private void setNeo4jService(Neo4jService neo4jService){
        this.neo4jService=neo4jService;
    }
    @Autowired
    private void setMySQLService(RelationService relationService) {
        this.relationService=relationService;
    }



    @GetMapping("/director-actor")
    public Result<Object> getTop20DirectorActorCollaborations() {
        List<Long> modelTimes = new ArrayList<>();
        List<String> modelLogs = new ArrayList<>();
        long startTime1 = System.currentTimeMillis();
        List< StaticDirectorActor> directorActors=relationService.getTop20DirectorActorCollaborations();
        modelTimes.add(System.currentTimeMillis() - startTime1);
        modelLogs.add("SELECT d.*, a.* " +
                "FROM Director d " +
                "JOIN StaticDirectorActor sda ON d.director_id = sda.director_id " +
                "JOIN Actor a ON sda.actor_id = a.actor_id " +
                "ORDER BY sda.collaboration_number DESC " +
                "LIMIT 20");


        //hive待写
        long startTime2 = System.currentTimeMillis();
        directorActors = hiveRelationService.getTop20DirectorActorCollaborations();
        modelTimes.add(System.currentTimeMillis() - startTime2);
        modelLogs.add("SELECT d.*, a.* " +
                "FROM Director d " +
                "JOIN StaticDirectorActor sda ON d.director_id = sda.director_id " +
                "JOIN Actor a ON sda.actor_id = a.actor_id " +
                "ORDER BY sda.collaboration_number DESC " +
                "LIMIT 20");

        long startTime3 = System.currentTimeMillis();
        List<com.ssw331.warehousebackend.Neo4jDTO.Collaboration_DA> data = neo4jService.searchCollaborationInDA();
        modelTimes.add(System.currentTimeMillis() - startTime3);
        modelLogs.add("MATCH (d:Director)-[:COLLABORATED_WITH]->(a:Actor) " +
                "WITH d, a, size(()-[:COLLABORATED_WITH]->(d)-[:COLLABORATED_WITH]->(a)) AS collaboration_number " +
                "ORDER BY collaboration_number DESC " +
                "LIMIT 20 " +
                "RETURN d, a");
        return ResultResponse.success(data, modelTimes, modelLogs);

    }



    @GetMapping("/actor-actor")
    public Result<Object> getTop20ActorActorCollaborations() {
        List<Long> modelTimes = new ArrayList<>();
        List<String> modelLogs = new ArrayList<>();
        long startTime1 = System.currentTimeMillis();
        List<StaticActorActor> actorActors=relationService.getTop20ActorActorCollaborations();
        modelTimes.add(System.currentTimeMillis() - startTime1);
        modelLogs.add("SELECT a.* " +
                "FROM Actor a " +
                "JOIN StaticActorActor saa ON a.actor_id = saa.actor_id " +
                "ORDER BY saa.collaboration_number DESC " +
                "LIMIT 20");


        //hive待写
        long startTime2 = System.currentTimeMillis();
        actorActors = hiveRelationService.getTop20ActorActorCollaborations();
        modelTimes.add(System.currentTimeMillis() - startTime2);
        modelLogs.add("SELECT a.* " +
                "FROM Actor a " +
                "JOIN StaticActorActor saa ON a.actor_id = saa.actor_id " +
                "ORDER BY saa.collaboration_number DESC " +
                "LIMIT 20");

        long startTime3 = System.currentTimeMillis();
        List<com.ssw331.warehousebackend.Neo4jDTO.Collaboration_AA> data = neo4jService.searchCollaborationInAA();
        modelTimes.add(System.currentTimeMillis() - startTime3);
        modelLogs.add("MATCH (a:Actor)-[:COLLABORATED_WITH]->(saa:StaticActorActor) " +
                "RETURN a " +
                "ORDER BY saa.collaboration_number DESC " +
                "LIMIT 20");
        return ResultResponse.success(data, modelTimes, modelLogs);

    }


    @GetMapping("/director-director")
    public Result<Object> getTop20DirectorDirectorCollaborations() {
        List<Long> modelTimes = new ArrayList<>();
        List<String> modelLogs = new ArrayList<>();
        long startTime1 = System.currentTimeMillis();
        List<StaticDirectorDirector> directorDirectors=relationService.getTop20DirectorDirectorCollaborations();
        modelTimes.add(System.currentTimeMillis() - startTime1);
        modelLogs.add("SELECT p.* " +
                "FROM Product p " +
                "JOIN StaticDirectorDirector sdd ON p.product_id = sdd.product_id " +
                "ORDER BY sdd.collaboration_number DESC " +
                "LIMIT 20");


        //hive待写
        long startTime2 = System.currentTimeMillis();
        directorDirectors = hiveRelationService.getTop20DirectorDirectorCollaborations();
        modelTimes.add(System.currentTimeMillis() - startTime2);
        modelLogs.add("SELECT p.* " +
                "FROM Product p " +
                "JOIN StaticDirectorDirector sdd ON p.product_id = sdd.product_id " +
                "ORDER BY sdd.collaboration_number DESC " +
                "LIMIT 20");

        long startTime3 = System.currentTimeMillis();
        List<com.ssw331.warehousebackend.Neo4jDTO.Collaboration_AA> data = neo4jService.searchCollaborationInAA();
        modelTimes.add(System.currentTimeMillis() - startTime3);
        modelLogs.add("MATCH (p:Product)-[:COLLABORATED_WITH]->(sdd:StaticDirectorDirector) " +
                "RETURN p " +
                "ORDER BY sdd.collaboration_number DESC " +
                "LIMIT 20");
        return ResultResponse.success(data, modelTimes, modelLogs);

    }


    @GetMapping("/hot-actor-actor")
    public Result<Object> getTopActorActorByTypes(@RequestParam String type) {
        List<Long> modelTimes = new ArrayList<>();
        List<String> modelLogs = new ArrayList<>();
        long startTime1 = System.currentTimeMillis();
        List<Map<String, Object>> directorDirectors=relationService.getMostCommentedActorPairByMovieType(type);
        modelTimes.add(System.currentTimeMillis() - startTime1);
        modelLogs.add("SELECT  " +
                "    SAA.actor_name1,  " +
                "    SAA.actor_name2,  " +
                "    SUM(P.comment_number) AS TotalComments " +
                "FROM " +
                "    StaticActorActor SAA " +
                "JOIN  " +
                "    MovieActor MA1 ON SAA.actor_name1 = MA1.actor_name " +
                "JOIN  " +
                "    Movie M ON MA1.movie_id = M.movie_id " +
                "JOIN  " +
                "    MovieProduct MP ON M.movie_id = MP.movie_id " +
                "JOIN  " +
                "    Product P ON MP.product_id = P.product_id " +
                "WHERE " +
                "    M.Type like "+type+
                "GROUP BY " +
                "    SAA.actor_name1, " +
                "    SAA.actor_name2 " +
                "ORDER BY " +
                "    TotalComments DESC" +
                "LIMIT 50; ");

        //hive待写
        long startTime2 = System.currentTimeMillis();
        directorDirectors = hiveRelationService.getMostCommentedActorPairByMovieType(type);
        modelTimes.add(System.currentTimeMillis() - startTime2);
        modelLogs.add("SELECT  " +
                "    SAA.actor_name1,  " +
                "    SAA.actor_name2,  " +
                "    SUM(P.comment_number) AS TotalComments " +
                "FROM " +
                "    StaticActorActor SAA " +
                "JOIN  " +
                "    MovieActor MA1 ON SAA.actor_name1 = MA1.actor_name " +
                "JOIN  " +
                "    Movie M ON MA1.movie_id = M.movie_id " +
                "JOIN  " +
                "    MovieProduct MP ON M.movie_id = MP.movie_id " +
                "JOIN  " +
                "    Product P ON MP.product_id = P.product_id " +
                "WHERE " +
                "    M.Type like "+type+
                "GROUP BY " +
                "    SAA.actor_name1, " +
                "    SAA.actor_name2 " +
                "ORDER BY " +
                "    TotalComments DESC" +
                "LIMIT 50; ");

        long startTime3 = System.currentTimeMillis();
        List<ReviewMax_AA> data = neo4jService.searchByReviewAA(type);
        modelTimes.add(System.currentTimeMillis() - startTime3);
        modelLogs.add("MATCH (m:Movie)-[:INCLUDE]->(p:Product) WHERE m.Type contains "+type+" With m.movie_id as mid, sum(p.Comments) AS sumC ORDER BY sum(p.Comments) DESC MATCH (a1:Actor)-[:ACTED_IN]->(n:Movie)<-[:ACTED_IN]-(a2:Actor) WHERE n.movie_id=mid RETURN a1.actor_name as actor1, a2.actor_name as actor2 LIMIT 50;");
        return ResultResponse.success(data, modelTimes, modelLogs);

    }

}
