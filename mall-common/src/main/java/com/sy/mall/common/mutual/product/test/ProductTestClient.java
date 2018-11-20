package com.sy.mall.common.mutual.product.test;

import com.sy.mall.common.platform.RP;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by dell on 2018/11/20.
 * @author dell
 */
@FeignClient(value = "mall-product",fallback = ProductTestClientFallback.class)
public interface ProductTestClient {

    @GetMapping("/demo/test2")
    RP test2() throws Exception;
}
