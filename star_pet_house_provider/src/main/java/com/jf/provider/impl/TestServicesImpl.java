package com.jf.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jf.provider.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.star_pet_house_commons.model.User;
import org.star_pet_house_service.services.TestServices;

import javax.annotation.Resource;
import java.util.List;

/*
 *@description:
 *@author jiafeng
 *@date 2020/1/20 0020 13:27
 */
@Service
public class TestServicesImpl implements TestServices {
    @Resource
    private UserMapper userMapper;

    @Override
    public String sayHello(String name) {
        List<User> userList = userMapper.queryAll();
        return "hello"+name;
    }
}
