package com.krypton.citysearchapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "City Search API", version = "1.0", description = "Enterprise City Search API for the Platform"))
public class CitySearchApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CitySearchApiApplication.class, args);
    }

}
