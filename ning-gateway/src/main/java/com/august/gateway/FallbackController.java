package com.august.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by zkning on 2020/6/13.
 */
@RestController
public class FallbackController {

    /**
     * 网管降级默认
     * @return
     */
    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        System.out.println("服务降级...");
        return Mono.just("Auth Fallback");
    }
}
