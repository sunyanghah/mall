package com.sy.mall.sale.test.application.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sy.mall.sale.entity.Test;
import com.sy.mall.sale.test.application.TestApplication;
import com.sy.mall.sale.test.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dell on 2018/10/27.
 * @author dell
 */
@Service
public class TestApplicationImpl extends ServiceImpl<TestMapper,Test> implements TestApplication {
    @Autowired
    private TestMapper testMapper;

    @Override
    public Integer getIdByName(String name) throws Exception {
        return testMapper.getIdByName(name);
    }
}
