package com.ssw331.warehousebackend;

import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPI31
public class WarehouseBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseBackendApplication.class, args);
	}

}
