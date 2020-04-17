package com.jf.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import org.star_pet_house_commons.model.User;
import org.star_pet_house_commons.model.response.PermissionEnumVO;

import java.util.List;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/1/22 0022 09:46
 */
public interface UserMapper extends BaseMapper {

    @SelectProvider(method = "getUserMenu",type = UserProvider.class)
    public List<PermissionEnumVO> getUserMenu(String userId);
}
