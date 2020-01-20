package com.jf.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.star_pet_house_service.services.TestServices;

/*
 *@description:
 *@author jiafeng
 *@date 2020/1/20 0020 13:27
 */
@Service
public class TestServicesImpl implements TestServices {
    @Override
    public String sayHello(String name) {
        return "hello"+name;
    }
}
