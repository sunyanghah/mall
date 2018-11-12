package com.sy.mall.security.web;

import com.sy.mall.common.platform.RP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2018/10/31.
 * @author dell
 */
@RestController
@Slf4j
public class TestController {

//    @PostMapping("/login")
//    public RP login() throws Exception{
//        log.info("-------login----------");
//        return RP.buildSuccess(true);
//    }

    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
    @GetMapping("/security/test")
    public RP test() throws Exception{
        return RP.buildSuccess("test");
    }
}
