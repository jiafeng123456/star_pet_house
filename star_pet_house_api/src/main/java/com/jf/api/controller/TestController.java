package com.jf.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.star_pet_house_service.services.TestServices;
import org.star_pet_house_commons.model.User;

import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/1/20 0020 13:23
 */
@Controller
public class TestController{

    @Reference()
    private TestServices testServices;

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/hello")
    public Map<String,Object> sayHello(String page_num){
        return testServices.sayHello(page_num);
    }
}
