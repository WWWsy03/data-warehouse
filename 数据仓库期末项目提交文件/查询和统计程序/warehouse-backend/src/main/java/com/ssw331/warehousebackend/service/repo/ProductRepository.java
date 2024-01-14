package com.ssw331.warehousebackend.service.repo;

import com.ssw331.warehousebackend.Neo4jDTO.Product;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends Neo4jRepository<Product, String> {
    @Query("MATCH (m:Movie)-[r:INCLUDE]->(p:Product) WHERE m.movie_name contains $movieName RETURN p;")
    List<Product> findProductsByMovieName(String movieName);
}
