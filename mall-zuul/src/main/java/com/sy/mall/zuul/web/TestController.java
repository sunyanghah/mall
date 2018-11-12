package com.sy.mall.zuul.web;

import com.sy.mall.common.platform.RP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2018/10/31.
 * @author dell
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/security/test")
    public RP test() throws Exception{
        System.out.println(1/0);
        return RP.buildSuccess("this is message","this is data");
    }
}
