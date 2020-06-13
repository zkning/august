package com.august.website.configure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zkning
 * swagger 配置
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true", matchIfMissing = true)
@Profile({"dev","test","inte"})
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        List<Parameter> parameters = new ArrayList<Parameter>();

        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        parameters.add(aParameterBuilder

                //参数类型支持header, cookie, body, query etc
                .parameterType("header")

                //参数名
                .name("WEB_TOKEN")

                //默认值
                .defaultValue("")
                .description("授权TOKEN")

                //指定参数值的类型
                .modelRef(new ModelRef("String"))
                .required(false)

                //非必需，这里是全局配置
                .build());


        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }
}
