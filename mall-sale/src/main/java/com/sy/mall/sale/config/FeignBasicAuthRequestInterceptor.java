package com.sy.mall.sale.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dell on 2018/11/20.
 * @author dell
 */
@Component
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

    private static String authorization = "Authorization";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String authToken = request.getHeader(authorization);
        if (authToken != null) {
            requestTemplate.header(authorization, authToken);
        }
    }
}
