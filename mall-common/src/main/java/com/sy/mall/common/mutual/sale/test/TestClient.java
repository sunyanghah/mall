package com.sy.mall.common.mutual.sale.test;

import com.sy.mall.common.platform.RP;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by dell on 2018/10/27.
 * @author dell
 */
@FeignClient(value = "mall-sale",fallback = TestClientFallback.class)
public interface TestClient {

    @GetMapping("/sale/test/info")
    RP test() throws Exception;

}
