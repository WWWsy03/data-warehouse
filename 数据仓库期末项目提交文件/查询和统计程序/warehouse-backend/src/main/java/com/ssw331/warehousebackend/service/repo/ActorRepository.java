package com.ssw331.warehousebackend.service.repo;

import com.ssw331.warehousebackend.Neo4jDTO.Actor;
import com.ssw331.warehousebackend.Neo4jDTO.Collaboration_AA;
import com.ssw331.warehousebackend.Neo4jDTO.Collaboration_DA;
import com.ssw331.warehousebackend.Neo4jDTO.ReviewMax_AA;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends Neo4jRepository<Actor, String> {



    @Query("MATCH (a1:Actor)-[r:COOPERATE]->(a2:Actor) WHERE a1.actor_name = $actorName RETURN a2.actor_name;")
    List<String> findActorsByActorName(String actorName);

    @Query("MATCH (d:Director)-[r:COOPERATE]->(a:Actor) RETURN d.director_name AS director, a.actor_name AS actor, r.counts AS collaborationNumber ORDER BY r.counts DESC LIMIT 51;")
    List<Collaboration_DA> findCollaborationBTDA();

    @Query("MATCH (a1:Actor)-[r:COOPERATE]->(a2:Actor) RETURN a1.actor_name AS actor1, a2.actor_name AS actor2, r.counts AS collaborationNumber ORDER BY r.counts DESC LIMIT 50;")
    List<Collaboration_AA> findCollaborationBTAA();

    @Query("MATCH (m:Movie)-[:INCLUDE]->(p:Product) WHERE m.Type contains $type With m.movie_id as mid, sum(p.Comments) AS sumC ORDER BY sum(p.Comments) DESC MATCH (a1:Actor)-[:ACTED_IN]->(n:Movie)<-[:ACTED_IN]-(a2:Actor) WHERE n.movie_id=mid RETURN a1.actor_name as actor1, a2.actor_name as actor2 LIMIT 50;")
    List<ReviewMax_AA> findActorsWithReviewsAndType(String type);

    @Query("MATCH (a:Actor)-[:ACTED_IN]->(m)<-[:DIRECTED]-(d:Director) WHERE d.director_name = $directorName RETURN a.actor_name;")
    List<String> findActorWithDirector(String directorName);
}
