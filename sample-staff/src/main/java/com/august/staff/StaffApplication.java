package com.august.staff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zkning
 */
@EnableSwagger2
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class StaffApplication {


    public static void main(String[] args) {
        SpringApplication.run(StaffApplication.class);
    }
}
