package com.sy.mall.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by dell on 2018/10/27.
 * @author dell
 */
@SpringBootApplication
@EnableZuulProxy
public class MallZuulApplication {
    public static void main(String[] args){
        SpringApplication.run(MallZuulApplication.class,args);
    }
}
