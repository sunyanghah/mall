package com.sy.mall.product.web;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;
import com.sy.mall.common.platform.RP;
import freemarker.template.utility.StringUtil;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2018/11/9.
 * @author dell
 */
@RestController
public class DemoController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/demo/test")
    public RP test() throws Exception{
        return RP.buildSuccess("this is demo message","this is demo data");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping("/demo/test2")
    public RP test2() throws Exception{
        return RP.buildSuccess("this is test2 message","this is test2 data");
    }

    @GetMapping("/demo/getUserInfo")
    public RP getUserInfo() throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication==null ? null : authentication.getPrincipal();
        if (null != principal){
            principal = JSON.parse(StringUtil.tryToString(principal));
        }
        return RP.buildSuccess(principal);
    }
}
