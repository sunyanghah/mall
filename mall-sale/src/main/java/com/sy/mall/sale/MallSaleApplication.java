package com.sy.mall.sale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by dell on 2018/10/27.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = "com.sy.mall")
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.sy.mall.common.mutual"})
public class MallSaleApplication {
    public static void main(String[] args){
        SpringApplication.run(MallSaleApplication.class,args);
    }
}
