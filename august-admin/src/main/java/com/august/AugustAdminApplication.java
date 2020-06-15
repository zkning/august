package com.august;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author sandstorm
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class AugustAdminApplication
{
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(AugustAdminApplication.class, args);
    }
}