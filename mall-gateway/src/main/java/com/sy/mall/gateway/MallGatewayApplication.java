package com.sy.mall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.internal.Function;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * Created by dell on 2018/10/27.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = "com.sy.mall")
public class MallGatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(MallGatewayApplication.class,args);
    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
////                .route(t -> t.path("/oauth/**")
////                        .and()
////                        .uri("lb://mall-redis-oauth2/")
////                )
//                .route(t -> t.path("/demo/**")
//                        .and()
//                        .uri("lb://mall-product/"))
//                .build();
//    }
}
