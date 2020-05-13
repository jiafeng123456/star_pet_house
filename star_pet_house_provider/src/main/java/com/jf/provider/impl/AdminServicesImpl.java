package com.jf.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jf.provider.cache.AllcitycodeCache;
import com.jf.provider.mapper.LogApplicationMapper;
import com.jf.provider.mapper.PetStoreMapper;
import com.jf.provider.mapper.SysUserMapper;
import com.jf.provider.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.star_pet_house_commons.constants.OperationFields;
import org.star_pet_house_commons.enums.CommonResultCode;
import org.star_pet_house_commons.enums.PetStoreStatus;
import org.star_pet_house_commons.enums.UserStatus;
import org.star_pet_house_commons.model.*;
import org.star_pet_house_commons.utils.MD5Util;
import org.star_pet_house_service.services.IAdminServices;

import javax.xml.ws.Action;
import java.util.List;
import java.util.Map;

import static org.star_pet_house_commons.constants.OperationFields.*;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/13 0013 09:48
 */
@Service
public class AdminServicesImpl extends BaseServiceImpl implements IAdminServices {

    @Autowired
    private PetStoreMapper petStoreMapper;
    @Autowired
    private LogApplicationMapper logApplicationMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private AllcitycodeCache allcitycodeCache;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Map<String, Object> getStoreApplicationList(PetStore petStore, int page_size, int page_num) {
        Map<String, Object> result = super.getSuccessResultMap();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("store_status", PetStoreStatus.STORE_APPLICATION.getCode());
        IPage<PetStore> petStorePage = new Page<>(page_num,page_size);
        petStorePage = petStoreMapper.selectPage(petStorePage, queryWrapper);
        List<PetStore> petStoreList = petStorePage.getRecords();
        for (PetStore petStore1 : petStoreList){
            Map<String, List<AllCityCode>> proviceList = allcitycodeCache.getProviceList();
            petStore1.setProvince_name(proviceList.get(petStore1.getProvince_no()).get(0).getProvince_name());
            petStore1.setCity_name(allcitycodeCache.getCityNameByCityCode(petStore1.getCity_no()));
            petStore1.setDistrict_name(allcitycodeCache.getByCitiCode(petStore1.getDistrict_no()).getDistrict_name());
        }
        result.put(RESULT,petStoreList);
        return result;
    }

    @Override
    public Map<String, Object> getStoreApplicationPaginator(PetStore petStore) {
        Map<String, Object> result = super.getSuccessResultMap();
        QueryWrapper queryWrapper = new QueryWrapper(petStore);
        queryWrapper.eq("store_status", PetStoreStatus.STORE_APPLICATION.getCode());
        int total = petStoreMapper.selectCount(queryWrapper);
        result.put("total", total);
        return result;
    }

    @Override
    public Map<String, Object> agreeApplication(String store_id, String op_type) {
        Map<String, Object> result = super.getSuccessResultMap();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("store_id", store_id);
        queryWrapper.eq("store_status", PetStoreStatus.STORE_APPLICATION.getCode());
        PetStore queryPetStore = petStoreMapper.selectOne(queryWrapper);
        if (queryPetStore == null){
            result.put(OperationFields.SUCCESS, false);
            result.put(OperationFields.ERROR_NO, CommonResultCode.STORE_APPLICATION_NOT_EXIST.getDesc());
            result.put(OperationFields.ERROR_INFO, CommonResultCode.STORE_APPLICATION_NOT_EXIST.getDesc());
            return result;
        }
        LogApplication logApplication = new LogApplication();
        if (op_type.equals("0")){
            //审批通过
            queryPetStore.setStore_status(PetStoreStatus.STORE_NORMAL.getCode());
            QueryWrapper sysqueryWrapper = new QueryWrapper();
            sysqueryWrapper.eq("uid", queryPetStore.getUser_id());
            SysUserRole sysUserRole = sysUserRoleMapper.selectOne(sysqueryWrapper);
            if (sysUserRole.getRole_id().equals(1)){
                sysUserRole.setRole_id(4);
            }else {
                sysUserRole.setRole_id(3);
            }
            sysUserRoleMapper.updateById(sysUserRole);
            logApplication.setApplication_no("0");
            logApplication.setApplication_info("审批通过");
            result.put(ERROR_INFO,"审批通过");
        }else {
            //审批不通过
            queryPetStore.setStore_status(PetStoreStatus.STORE_APPLICATION_FAIL.getCode());
            logApplication.setApplication_no("-1");
            logApplication.setApplication_info("审批不通过");
            result.put(ERROR_INFO,"审批不通过");
        }
        petStoreMapper.updateById(queryPetStore);

        logApplication.setStore_id(queryPetStore.getStore_id());
        logApplication.setAccept_user_id(0);
        logApplicationMapper.insert(logApplication);
        return result;
    }

    @Override
    public Map<String, Object> dealUser(String user_id,
                                        String op_type) {
        Map<String, Object> result = super.getSuccessResultMap();
        SysUser sysUser = sysUserMapper.selectById(user_id);
        if (sysUser == null){
            result.put(SUCCESS, false);
            result.put(ERROR_NO, CommonResultCode.USER_NOT_EXIST.getCode());
            result.put(ERROR_INFO, CommonResultCode.USER_NOT_EXIST.getDesc());
        }
        //op_type 0冻结 1注销 2 重置密码
        if (op_type.equals("0")){
            sysUser.setStatus(UserStatus.USER_FREEZE.getCode());
        }else if(op_type.equals("1")){
            sysUser.setStatus(UserStatus.USER_LOGOUT.getCode());
        }else if (op_type.equals("2")){
            Map<String, String> md5Pass = MD5Util.generate("123456");
            sysUser.setSalt(md5Pass.get("salt"));
            sysUser.setPassword(md5Pass.get("password"));
        }
        sysUserMapper.updateById(sysUser);
        return result;
    }
}
