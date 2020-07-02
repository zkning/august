package com.august.gateway;

import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2020/6/13.
 */
@Component
public class AuthorizService {

    public boolean doAuth(String sid) {
        System.out.println("[" + sid + "]授权通过");
        return true;
    }
}
