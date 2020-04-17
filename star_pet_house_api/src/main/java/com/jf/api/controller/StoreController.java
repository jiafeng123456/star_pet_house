package com.jf.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.star_pet_house_commons.model.PetItem;
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
    @RequestMapping("/saveStoreApplication")
    public Map<String, Object> saveStoreApplication(PetStore petStore, String op_type){
        return petStoreService.saveStoreApplication(petStore, op_type);
    }

    @ApiOperation(value = "店铺列表")
    @RequestMapping(value = "/getStoreList")
    public Map<String, Object> getStoreList(PetStore petStore,@RequestParam(defaultValue = "10") int page_size,
                                            @RequestParam(defaultValue = "0") int page_num){
        return petStoreService.getStoreList(petStore, page_size, page_num);
    }

    @ApiOperation(value = "店铺数量")
    @RequestMapping(value = "/getStorePaginator")
    public Map<String, Object> getStorePaginator(PetStore petStore){
        return petStoreService.getStorePaginator(petStore);
    }

    @ApiOperation(value = "宠物上架")
    @RequestMapping(value = "petOnShelves")
    public Map<String, Object> petOnShelves(PetItem petItem, String op_type){
        return petStoreService.petOnShelves(petItem, op_type);
    }
}
