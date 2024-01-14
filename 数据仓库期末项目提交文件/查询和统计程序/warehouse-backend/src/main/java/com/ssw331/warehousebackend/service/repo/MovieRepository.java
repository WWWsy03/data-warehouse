package com.ssw331.warehousebackend.service.repo;

import com.ssw331.warehousebackend.Neo4jDTO.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends Neo4jRepository<Movie, String> {
    @Query("MATCH (m:Movie)<-[r:DIRECTED]-(a:Director) WHERE a.director_name = $directorName RETURN m.movie_name;")
    List<String> findMoviesByDirector(String directorName);

    int countMoviesByReleaseTimeContaining(String date);

    List<Movie> findMoviesByTypeContaining(String Type);

    int countMoviesByTypeContainingAndReleaseTimeContaining(String Type, String year);

    @Query("MATCH (m:Movie)<-[r:DIRECTED]-(d:Director) WHERE m.release_time contains $year and d.director_name = $directorName return count(m);")
    int countMoviesWithYearAndDirector(String year, String directorName);

    @Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Actor) WHERE a.actor_name = $actorName RETURN m.movie_name;")
    List<String> findMoviesByActor(String actorName);

    @Query("MATCH (m:Movie)-[r:INCLUDE]->(p:Product) WHERE p.Grade >= $grade RETURN m.movie_name;")
    List<String> findWithGrade(double grade);

}
