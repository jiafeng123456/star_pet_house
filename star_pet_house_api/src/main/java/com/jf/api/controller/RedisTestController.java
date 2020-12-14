package com.jf.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.star_pet_house_service.services.IRedisServices;

/*
 *@description: 测试redis接口
 *@author jiafeng
 *@date 2020/12/14 0014 09:39
 */

@RestController("redis")
public class RedisTestController {

    @Reference
    private IRedisServices redisServices;

    @RequestMapping("/test_redis")
    public void TestRedis(){
        redisServices.testRedis();
    }
}
