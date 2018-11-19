package com.sy.mall.redis.oauth2.client.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.mall.redis.oauth2.entity.SysOauthClientDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by dell on 2018/11/12.
 * @author dell
 */
@Mapper
public interface ClientMapper extends BaseMapper<SysOauthClientDetails> {
}
