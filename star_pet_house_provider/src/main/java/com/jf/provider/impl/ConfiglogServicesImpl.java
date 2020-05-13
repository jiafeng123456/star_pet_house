package com.jf.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jf.provider.mapper.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.star_pet_house_commons.enums.GengerType;
import org.star_pet_house_commons.enums.UserStatus;
import org.star_pet_house_commons.model.*;
import org.star_pet_house_commons.model.response.*;
import org.star_pet_house_service.services.IConfiglogServices;

import java.util.List;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/5/12 0012 23:48
 */
@Service
public class ConfiglogServicesImpl extends BaseServiceImpl implements IConfiglogServices {

    @Autowired
    private LogApplicationMapper logApplicationMapper;
    @Autowired
    private LogComplainMapper logComplainMapper;
    @Autowired
    private LogWalletMapper logWalletMapper;
    @Autowired
    private LogLoginMapper logLoginMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Map<String, Object> getApplicationlog(LogApplicationVO logApplicationVO, int page_size, int page_num) {
        Map<String,Object> result = super.getSuccessResultMap();
        Page<LogApplicationVO> page = new Page<LogApplicationVO>(page_num,page_size);
        List<LogApplicationVO> logApplicationList = logApplicationMapper.queryPageList(page,logApplicationVO);
        result.put("count", (int)page.getTotal());
        result.put("code", 0);
        result.put("data", logApplicationList);
        return result;
    }

    @Override
    public Map<String, Object> getLoginlog(LogLoginVO logLoginVO,
                                           int page_size,
                                           int page_num,String date_time) {
        Map<String,Object> result = super.getSuccessResultMap();
        String star_date = null;
        String end_date = null;
        if (StringUtils.isNotBlank(date_time)){
            star_date = date_time + " 00:00:00";
            end_date = date_time + " 23:59:59";
        }
        Page<LogLoginVO> page = new Page<LogLoginVO>(page_num,page_size);
        List<LogLoginVO> logLoginVOList = logLoginMapper.queryPageList(page,logLoginVO,star_date,end_date);
        result.put("count", (int)page.getTotal());
        result.put("code", 0);
        result.put("data", logLoginVOList);
        return result;
    }

    @Override
    public Map<String, Object> getWalletlog(LogWalletVO logWalletVO, int page_size, int page_num,String date_time) {
        Map<String,Object> result = super.getSuccessResultMap();
        String star_date = null;
        String end_date = null;
        if (StringUtils.isNotBlank(date_time)){
            star_date = date_time + " 00:00:00";
            end_date = date_time + " 23:59:59";
        }
        Page<LogWalletVO> page = new Page<LogWalletVO>(page_num,page_size);
        List<LogWalletVO> logWalletVOList = logWalletMapper.queryPageList(page,logWalletVO,star_date,end_date);
        result.put("count", (int)page.getTotal());
        result.put("code", 0);
        result.put("data", logWalletVOList);
        return result;
    }

    @Override
    public Map<String, Object> getComplainlog(LogComplainVO logComplainVO, int page_size, int page_num,String date_time) {
        Map<String,Object> result = super.getSuccessResultMap();
        String star_date = null;
        String end_date = null;
        if (StringUtils.isNotBlank(date_time)){
            star_date = date_time + " 00:00:00";
            end_date = date_time + " 23:59:59";
        }
        Page<LogComplainVO> page = new Page<LogComplainVO>(page_num,page_size);
        List<LogComplainVO> logComplainVOList = logComplainMapper.queryPageList(page,logComplainVO,star_date,end_date);
        result.put("count", (int)page.getTotal());
        result.put("code", 0);
        result.put("data", logComplainVOList);
        return result;
    }

    @Override
    public Map<String, Object> getUserinfoList(UserInfoVO userInfoVO,
                                               int page_size, int page_num,String date_time) {
        Map<String,Object> result = super.getSuccessResultMap();
        String star_date = null;
        String end_date = null;
        if (StringUtils.isNotBlank(date_time)){
            star_date = date_time + " 00:00:00";
            end_date = date_time + " 23:59:59";
        }
        Page<UserInfoVO> page = new Page<>(page_num,page_size);
        List<UserInfoVO> userInfoVOList = userInfoMapper.queryPageList(page,userInfoVO,star_date,end_date);
        for (UserInfoVO userInfoVO1 : userInfoVOList){
            userInfoVO1.setUser_status(UserStatus.getNameByCode(Integer.parseInt(userInfoVO1.getUser_status())));
            userInfoVO1.setUser_gender(GengerType.getNameByCode(userInfoVO1.getUser_gender()));
        }
        result.put("count", (int)page.getTotal());
        result.put("code", 0);
        result.put("data", userInfoVOList);
        return result;
    }
}
