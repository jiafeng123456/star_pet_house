package com.jf.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.star_pet_house_commons.model.PetStore;
import org.star_pet_house_service.services.IPetStoreService;

import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/5 0005 17:48
 */
@CrossOrigin
@RestController
@Api(value = "商店接口", tags = "商店接口")
@RequestMapping("store")
public class StoreController{

    @Reference
    private IPetStoreService petStoreService;

    @ApiOperation(value = "店铺申请接口")
    @RequestMapping("/store_application")
    public Map<String, Object> saveStoreApplication(PetStore petStore, String op_type){
        return petStoreService.saveStoreApplication(petStore, op_type);
    }

    @ApiOperation(value = "店铺列表")
    @RequestMapping(value = "/get_store_list")
    public Map<String, Object> getStoreList(PetStore petStore,@RequestParam(defaultValue = "10") int page_size,
                                            @RequestParam(defaultValue = "0") int page_num){
        return petStoreService.getStoreList(petStore, page_size, page_num);
    }

    @ApiOperation(value = "店铺数量")
    @RequestMapping(value = "/get_store_paginator")
    public Map<String, Object> getStorePaginator(PetStore petStore){
        return petStoreService.getStorePaginator(petStore);
    }
}
