package com.jf.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jf.provider.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.star_pet_house_commons.model.User;
import org.star_pet_house_service.services.TestServices;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String,Object> sayHello(String page_num) {
        Map<String,Object> map = new HashMap<>();
        List<User> userList = new ArrayList<>();
        User user = new User();
        if (page_num.equals("1")){
            for (int i = 0 ; i < 20 ; i ++) {
                user.setUser_name("jiafeng");
                userList.add(user);
            }
        }else {
            user.setUser_name("zhuqiuyan");
            userList.add(user);
        }

//        List<User> userList = userMapper.queryAll();
        map.put("userList",userList);
        return map;
    }
}
