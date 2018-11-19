package com.sy.mall.redis.oauth2.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.mall.redis.oauth2.entity.SysRole;
import com.sy.mall.redis.oauth2.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by dell on 2018/10/31.
 * @author dell
 */
@Mapper
public interface UserMapper extends BaseMapper<SysUser>{

    /**
     * 根据用户账号获取用户信息
     * @param username
     * @return
     */
    SysUser getUserByUsername(String username);

    /**
     * 根据用户id获取角色信息
     * @param userId
     * @return
     */
    List<SysRole> getRolesByUserId(Integer userId);
}
