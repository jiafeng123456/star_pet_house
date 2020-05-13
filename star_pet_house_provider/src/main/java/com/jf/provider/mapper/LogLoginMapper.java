package com.jf.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.star_pet_house_commons.model.LogLogin;
import org.star_pet_house_commons.model.response.LogComplainVO;
import org.star_pet_house_commons.model.response.LogLoginVO;

import java.util.List;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 21:34
 */
public interface LogLoginMapper extends BaseMapper<LogLogin> {

    @SelectProvider(method = "queryPageList",type = LogLoginProvider.class)
    List<LogLoginVO> queryPageList(Page<LogLoginVO> page, @Param("logLoginVO") LogLoginVO logLoginVO,@Param("star_date") String star_date,@Param("end_date") String end_date);
}
