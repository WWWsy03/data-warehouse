package com.ssw331.warehousebackend.Neo4jDTO;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@Node("Movie")
public class Movie {
    @Id
    @Property("movie_id")
    private String movieId;

    @Property("Type")
    private String type;

    @Property("movie_name")
    private String movieName;

    @Property("release_time")
    private String releaseTime;

    @Property
    private String time;
}
