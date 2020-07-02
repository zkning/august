package com.august.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

/**
 * authorize filter
 * Created by zkning on 2020/6/13.
 */
@Component
public class AuthorizGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthorizGatewayFilterFactory.Config> {

    @Autowired
    AuthorizService authorizService;

    public AuthorizGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(AuthorizGatewayFilterFactory.Config config) {
        return ((exchange, chain) -> {
            if (config.isValve()) {

                //TODO 授权

                authorizService.doAuth(exchange.getRequest().getURI().getSchemeSpecificPart());
                return chain.filter(exchange);
            }
            ServerHttpResponse serverHttpResponse = exchange.getResponse();
            System.out.println("[" + exchange.getRequest().getURI().getSchemeSpecificPart() + "]未授权");
            serverHttpResponse.setStatusCode(HttpStatus.FORBIDDEN);
            return serverHttpResponse.setComplete();
        });
    }

    @Override
    public ShortcutType shortcutType() {
        return ShortcutType.DEFAULT;
    }

    public static class Config {
        private boolean valve;

        public boolean isValve() {
            return valve;
        }

        public void setValve(boolean valve) {
            this.valve = valve;
        }
    }
}