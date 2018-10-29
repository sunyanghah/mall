package com.sy.mall.common.mutual.sale.test;

import com.sy.mall.common.platform.RP;
import org.springframework.stereotype.Component;

/**
 * Created by dell on 2018/10/27.
 * @author dell
 */
@Component
public class TestClientFallback implements TestClient{
    @Override
    public RP test() throws Exception {
        return null;
    }
}
