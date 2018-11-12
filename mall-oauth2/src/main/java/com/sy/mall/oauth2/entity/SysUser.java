package com.sy.mall.oauth2.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * Created by dell on 2018/10/31.
 * @author dell
 */
@TableName("sys_user")
@Data
public class SysUser{

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

}
