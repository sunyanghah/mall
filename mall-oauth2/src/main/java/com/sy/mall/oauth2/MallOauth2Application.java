package com.sy.mall.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by dell on 2018/10/27.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = "com.sy.mall")
public class MallOauth2Application {
    public static void main(String[] args){
        SpringApplication.run(MallOauth2Application.class,args);
    }
}
