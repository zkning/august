package com.august.website.configure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebSiteConfig {

    @Bean
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(1000 * 5);
        simpleClientHttpRequestFactory.setReadTimeout(1000 * 30);
        return new RestTemplate(simpleClientHttpRequestFactory);
    }

}
