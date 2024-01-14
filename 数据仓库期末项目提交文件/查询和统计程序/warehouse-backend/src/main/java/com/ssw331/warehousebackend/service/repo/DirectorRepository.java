package com.ssw331.warehousebackend.service.repo;

import com.ssw331.warehousebackend.Neo4jDTO.Collaboration_DD;
import com.ssw331.warehousebackend.Neo4jDTO.Director;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface DirectorRepository extends Neo4jRepository<Director, String> {
    @Query("MATCH (d1:Director)-[:COOPERATE]->(d2:Director) WHERE d1.director_name = $directorName RETURN d2.director_name;")
    List<String> findDirectorsByDirectorName(String directorName);

    @Query("MATCH (a:Actor)-[:ACTED_IN]->(m)<-[:DIRECTED]-(d:Director) WHERE a.actor_name = $actorName RETURN d.director_name;")
    List<String> findDirectorsWithActor(String actorName);

    @Query("MATCH (d1:Director)-[r:COOPERATE]->(d2:Director) RETURN d1.director_name AS director1, d2.director_name AS director2, r.counts AS collaborationNumber ORDER BY r.counts DESC LIMIT 50;")
    List<Collaboration_DD> findCollaborationBTDD();
}
