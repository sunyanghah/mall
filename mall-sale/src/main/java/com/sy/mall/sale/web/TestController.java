package com.sy.mall.sale.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sy.mall.common.mutual.product.test.ProductTestClient;
import com.sy.mall.common.platform.RP;
import com.sy.mall.sale.entity.Test;
import com.sy.mall.sale.test.application.TestApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dell on 2018/10/27.
 * @author dell
 */
@RestController
@RequestMapping("/sale/test")
@Slf4j
public class TestController {

    @Autowired
    private TestApplication testApplication;

    @Autowired
    private ProductTestClient productTestClient;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/info")
    public RP test() throws Exception{
        List<Test> testList = testApplication.selectList(new EntityWrapper<Test>());
        return RP.buildSuccess(testList);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/ttt")
    public RP ttt() throws Exception{
        return RP.buildSuccess(testApplication.getIdByName("张三"));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/product")
    public RP product() throws Exception{
        RP rp = productTestClient.test2();
        return rp;
    }
}
