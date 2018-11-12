package com.sy.mall.zuul.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by dell on 2018/10/29.
 * @author dell
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{


    // 使用DaoAuthenticationProvider时，实现userDetailsService
    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
    }

    @Bean
    JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }

    @Bean
    MyAuthenticationException myAuthenticationException(){
        return new MyAuthenticationException();
    }

    @Bean
    MyAuthorizationExceptionFilter myAccessDeniedException(){
        return new MyAuthorizationExceptionFilter();
    }


    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf验证
        http.csrf().disable()
                // 对请求进行认证
                .authorizeRequests()
                // 所有 / 的所有请求 都放行
                .antMatchers("/").permitAll()
//                // 所有 /login 的POST请求 都放行
//                .antMatchers(HttpMethod.POST, "/login").permitAll()
                // 所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法
                .addFilterBefore(jwtAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(myAccessDeniedException(),JWTAuthenticationFilter.class);
    }
// 下面注释的两段代码为指定provider，不指定。
//    @Bean
//    CustomAuthenticationProvider getCustomAuthenticationProvider(){
//        return new CustomAuthenticationProvider();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(getCustomAuthenticationProvider());
//    }

}
