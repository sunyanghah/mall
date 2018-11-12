package com.sy.mall.oauth2.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * Created by dell on 2018/11/8.
 * @author dell
 */
@Data
@TableName("sys_resources")
public class SysResources {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("url")
    private String url;
}
