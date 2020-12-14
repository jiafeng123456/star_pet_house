package com.jf.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.star_pet_house_commons.model.AllCityCode;
import org.star_pet_house_service.services.IRedisServices;

/*
 *@description:
 *@author jiafeng
 *@date 2020/12/14 0014 09:43
 */
@Service
public class RedisServicesImpl implements IRedisServices {

    private static String key = "test:redis:1";
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void testRedis() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey){
            System.out.println("获取到key为：" +key+"的内容：" + operations.get(key));
        }else {
            operations.set(key, "看看而已");
            System.out.println("存入到key为：" +key+"的内容：" + operations.get(key));
        }
    }
}
