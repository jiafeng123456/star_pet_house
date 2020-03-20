package com.jf.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.star_pet_house_commons.error.BusinessException;
import org.star_pet_house_commons.error.EmBusinessError;
import org.star_pet_house_commons.response.CommonReturnType;
import org.star_pet_house_service.services.IBaseServices;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/3/19 0019 18:45
 */
@Service
public class BaseServicesImpl implements IBaseServices {
    @Override
    public Object handlerException(HttpServletRequest request,
                                   Exception ex) {
        Map<String,Object> responseData = new HashMap<>();
        if (ex instanceof BusinessException){
            //强制转换
            BusinessException businessException= (BusinessException)ex;
            responseData.put("errCode",businessException.getCode());
            responseData.put("errMsg",businessException.getMsg());
        }else{
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getCode());
            responseData.put("errMsg",EmBusinessError.UNKNOWN_ERROR.getMsg());
        }

        return CommonReturnType.create(responseData,"fail");
    }
}
