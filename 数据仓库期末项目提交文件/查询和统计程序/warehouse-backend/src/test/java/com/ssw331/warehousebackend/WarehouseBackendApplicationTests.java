package com.ssw331.warehousebackend;

import com.ssw331.warehousebackend.service.Neo4jService;
import com.ssw331.warehousebackend.service.repo.ActorRepository;
import com.ssw331.warehousebackend.service.repo.MovieRepository;
import com.ssw331.warehousebackend.service.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {MovieRepository.class, ProductRepository.class, ActorRepository.class})
@SpringBootTest(classes = WarehouseBackendApplication.class)
class WarehouseBackendApplicationTests {

    @Autowired
    Neo4jService neo4jService;

    @Test
    public void contextLoads() {
        System.out.println(neo4jService.searchMoviesByName("Harry Potter"));
    }

}
