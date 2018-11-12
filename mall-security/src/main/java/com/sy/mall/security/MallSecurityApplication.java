package com.sy.mall.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by dell on 2018/10/31.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = "com.sy.mall")
public class MallSecurityApplication {
    public static void main(String[] args){
        SpringApplication.run(MallSecurityApplication.class,args);
    }
}
