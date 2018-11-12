package com.sy.mall.sale.test.application;

import com.baomidou.mybatisplus.service.IService;
import com.sy.mall.sale.entity.Test;

/**
 * Created by dell on 2018/10/27.
 * @author dell
 */
public interface TestApplication extends IService<Test> {

    Integer getIdByName(String name) throws Exception;
}
