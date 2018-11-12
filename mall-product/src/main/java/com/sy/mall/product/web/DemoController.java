package com.sy.mall.product.web;

import com.sy.mall.common.platform.RP;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2018/11/9.
 * @author dell
 */
@RestController
public class DemoController {

    @GetMapping("/demo/test")
    public RP test() throws Exception{
        return RP.buildSuccess("this is message","this is data");
    }
}
