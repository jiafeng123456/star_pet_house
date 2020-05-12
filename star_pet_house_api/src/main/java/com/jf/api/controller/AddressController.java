package com.jf.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.star_pet_house_service.services.IAddressServices;

import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/5/4 0004 22:25
 */
@CrossOrigin
@RequestMapping("/address")
@RestController
public class AddressController {

    @Reference
    private IAddressServices addressServices;

    @ApiOperation(value = "获得省")
    @RequestMapping("/queryByParentId")
    public Map<String, Object> queryByParentId(){
        return addressServices.queryByParentId();
    }

    @ApiOperation(value = "获得市")
    @RequestMapping("/queryCityByParentId")
    public Map<String, Object> queryCityByParentId(String province_no){
        return addressServices.queryCityByParentId(province_no);
    }

    @ApiOperation(value = "获得区")
    @RequestMapping("/queryDistriceByParentId")
    public Map<String, Object> queryDistriceByParentId(String city_no){
        return addressServices.queryDistriceByParentId(city_no);
    }


}
