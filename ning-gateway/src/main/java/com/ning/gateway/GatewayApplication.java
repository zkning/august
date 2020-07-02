package com.ning.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author zkning
 */
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }


    @Bean
    public DatabaseouteDefinitionRepository inDatabaseouteDefinitionRepository() {
        return new DatabaseouteDefinitionRepository();
    }
}



