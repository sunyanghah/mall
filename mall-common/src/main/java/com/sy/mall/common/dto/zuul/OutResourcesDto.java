package com.sy.mall.common.dto.zuul;

import lombok.Data;

import java.util.List;

/**
 * Created by dell on 2018/11/8.
 * @author dell
 */
@Data
public class OutResourcesDto {

    private String roleName;

    private List<String> resourceList;
}
