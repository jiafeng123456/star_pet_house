package com.jf.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.star_pet_house_commons.model.UserInfo;
import org.star_pet_house_commons.model.response.LogComplainVO;
import org.star_pet_house_commons.model.response.UserInfoVO;

import java.util.List;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 16:42
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @SelectProvider(method = "queryPageList",type = UserInfoProvider.class)
    List<UserInfoVO> queryPageList(Page<UserInfoVO> page, @Param("userInfoVO") UserInfoVO userInfoVO, @Param("star_date") String star_date, @Param("end_date") String end_date);

}
