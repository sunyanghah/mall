package com.sy.mall.product.web;

import com.sy.mall.common.dto.test.InTestDto;
import com.sy.mall.common.platform.RP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2018/10/29.
 * @author dell
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class TestLoginController {

    @PostMapping()
    public RP login(@RequestBody InTestDto inTestDto) throws Exception{
        log.info("---------------------login-----------------------{}----{}",inTestDto.getUserName(),inTestDto.getPassword());
        return RP.buildSuccess(true);
    }
}
