package com.august.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 消息服务
 * @author zkning
 */
@EnableSwagger2
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class MessageApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class);
    }
}
