package com.jf.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.star_pet_house_commons.model.*;
import org.star_pet_house_commons.model.response.*;
import org.star_pet_house_service.services.IConfiglogServices;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/5/12 0012 23:46
 */
@RestController
@CrossOrigin
@RequestMapping("/configlog")
public class ConfigLogController {

    @Reference
    private IConfiglogServices configlogServices;

    @RequestMapping("/getApplicationlog")
    public Map<String, Object> getApplicationlog(LogApplicationVO logApplicationVO, @RequestParam(defaultValue = "10") int page_size,
                                                 @RequestParam(defaultValue = "0") int page_num){
        return configlogServices.getApplicationlog(logApplicationVO, page_size, page_num);
    }

    @RequestMapping("/getLoginlog")
    public Map<String, Object> getLoginlog(LogLoginVO logLoginVO, @RequestParam(defaultValue = "10") int page_size,
                                           @RequestParam(defaultValue = "0") int page_num,String date_time){
        return configlogServices.getLoginlog(logLoginVO, page_size, page_num,date_time);
    }

    @RequestMapping("/getWalletlog")
    public Map<String, Object> getWalletlog(LogWalletVO logWalletVO, @RequestParam(defaultValue = "10") int page_size,
                                            @RequestParam(defaultValue = "0") int page_num,String date_time){
        return configlogServices.getWalletlog(logWalletVO, page_size, page_num,date_time);
    }

    @RequestMapping("/getComplainlog")
    public Map<String, Object> getComplainlog(LogComplainVO logComplainVO, @RequestParam(defaultValue = "10") int page_size,
                                              @RequestParam(defaultValue = "0") int page_num,String date_time){
        return configlogServices.getComplainlog(logComplainVO, page_size, page_num,date_time);
    }

    @RequestMapping("/getUserinfoList")
    public Map<String, Object> getUserinfoList(UserInfoVO userInfoVO, @RequestParam(defaultValue = "10") int page_size,
                                               @RequestParam(defaultValue = "0") int page_num, String date_time){
        return configlogServices.getUserinfoList(userInfoVO, page_size, page_num,date_time);
    }
}
