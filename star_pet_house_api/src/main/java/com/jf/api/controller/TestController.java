package com.jf.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.star_pet_house_service.services.TestServices;

import javax.annotation.Resource;

/*
 *@description:
 *@author jiafeng
 *@date 2020/1/20 0020 13:23
 */
@Controller
public class TestController {

    @Reference
    private TestServices testServices;

    @ResponseBody
    @RequestMapping("/hello")
    public String sayHello(){
        System.out.println("---------------");
        return testServices.sayHello("jf");
    }
}
