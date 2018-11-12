package com.sy.mall.gateway.config;

import com.netflix.zuul.ZuulFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by dell on 2018/11/9.
 */
@Component
public class AuthFilter extends ZuulFilter {
    //拦截类型
    @Override
    public String filterType() {
        return org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
    }

    //拦截顺序
    @Override
    public int filterOrder() {
        return org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    //开启拦截
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //拦截处理逻辑
    @Override
    public Object run() {
        //获取用户认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        return null;
    }
}