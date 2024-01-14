package com.ssw331.warehousebackend.Neo4jDTO;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@Node("Director")
public class Director {
    @Id
    @Property("director_name")
    private String directorName;
}
