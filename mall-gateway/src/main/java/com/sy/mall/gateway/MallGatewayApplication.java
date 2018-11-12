package com.sy.mall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by dell on 2018/10/27.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = "com.sy.mall")
@EnableZuulProxy
public class MallGatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(MallGatewayApplication.class,args);
    }
}
