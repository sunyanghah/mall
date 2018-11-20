package com.sy.mall.common.mutual.product.test;

import com.sy.mall.common.platform.RP;
import org.springframework.stereotype.Component;

/**
 * Created by dell on 2018/11/20.
 * @author dell
 */
@Component
public class ProductTestClientFallback implements ProductTestClient {
    @Override
    public RP test2() throws Exception {
        return null;
    }
}
