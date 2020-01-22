package com.jf.provider.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import org.star_pet_house_commons.model.User;

import java.util.List;

/*
 *@description:
 *@author jiafeng
 *@date 2020/1/22 0022 09:46
 */
@Repository
public interface UserMapper {

    @SelectProvider(method = "queryAll",type = UserProvider.class)
    public List<User> queryAll();
}
