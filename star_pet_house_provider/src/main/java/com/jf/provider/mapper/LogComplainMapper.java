package com.jf.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.star_pet_house_commons.model.LogComplain;
import org.star_pet_house_commons.model.LogWallet;
import org.star_pet_house_commons.model.response.LogComplainVO;

import java.util.List;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 21:43
 */
public interface LogComplainMapper extends BaseMapper<LogComplain> {

    @SelectProvider(method = "queryPageList",type = LogComplainProvider.class)
    List<LogComplainVO> queryPageList(Page<LogComplainVO> page, @Param("logComplainVO") LogComplainVO logComplainVO,@Param("star_date") String star_date,@Param("end_date") String end_date);
}
