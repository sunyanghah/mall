package com.sy.mall.oauth2.resources.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sy.mall.common.dto.zuul.OutResourcesDto;
import com.sy.mall.oauth2.entity.SysResources;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by dell on 2018/11/8.
 * @author dell
 */
@Mapper
public interface ResourcesMapper extends BaseMapper<SysResources>{

    List<String> getResourceListByRole(List<String> roleList);

    List<OutResourcesDto> getAllResource();
}
