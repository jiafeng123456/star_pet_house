package com.jf.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.star_pet_house_commons.model.LogApplication;
import org.star_pet_house_commons.model.response.LogApplicationVO;

import java.util.List;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/13 0013 18:35
 */
public interface LogApplicationMapper extends BaseMapper<LogApplication> {

    @SelectProvider(method = "queryPageList",type = LogApplicationProvider.class)
    List<LogApplicationVO> queryPageList(Page<LogApplicationVO> page, @Param("logApplicationVO") LogApplicationVO logApplicationVO);
}
