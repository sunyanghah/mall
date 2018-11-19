package com.sy.mall.redis.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by dell on 2018/10/27.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = "com.sy.mall")
public class MallRedisOauth2Application {
    public static void main(String[] args){
        SpringApplication.run(MallRedisOauth2Application.class,args);
    }
}
