package com.jf.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.star_pet_house_commons.model.PetStore;
import org.star_pet_house_service.services.IAdminServices;

import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/13 0013 09:27
 */
@CrossOrigin
@RestController
@Api(value = "管理员接口", tags = "管理员接口")
@RequestMapping("admin")
public class AdminController {

    @Reference
    private IAdminServices adminServices;

    @ApiOperation(value = "店铺申请列表")
    @RequestMapping("/getStoreApplicationList")
    public Map<String, Object> getStoreApplicationList(PetStore petStore, @RequestParam(defaultValue = "10") int page_size,
                                                       @RequestParam(defaultValue = "0") int page_num){
        return adminServices.getStoreApplicationList(petStore, page_size, page_num);
    }

    @ApiOperation(value = "店铺申请数据")
    @RequestMapping(value = "/getStoreApplicationPaginator")
    public Map<String, Object> getStoreApplicationPaginator(PetStore petStore){
        return adminServices.getStoreApplicationPaginator(petStore);
    }

    @ApiOperation(value = "店铺审批结果")
    @RequestMapping(value = "/agreeApplication")
    public Map<String, Object> agreeApplication(String store_id, String op_type){
        return adminServices.agreeApplication(store_id, op_type);
    }

}
