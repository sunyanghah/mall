package com.sy.mall.product.web;

import com.sy.mall.common.mutual.sale.test.TestClient;
import com.sy.mall.common.platform.RP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dell on 2018/10/27.
 * @author dell
 */
@RestController
@RequestMapping("/product/test")
@Slf4j
public class TestController {

    @Autowired
    private TestClient testClient;

    @GetMapping("/info")
    public RP getInfo() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("---------------{}--------------",sdf.format(new Date()));
        RP rp = testClient.test();
        log.info("---------------{}--------------",sdf.format(new Date()));
        return rp;
    }

    @GetMapping("/auth")
    public RP auth() throws Exception{
        return RP.buildSuccess("this is message","this is data");
    }
}
