package com.august.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zkning
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class RegistryApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(RegistryApplication.class, args);

    }
}
