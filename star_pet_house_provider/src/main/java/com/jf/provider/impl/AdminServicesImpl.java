package com.jf.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jf.provider.mapper.LogApplicationMapper;
import com.jf.provider.mapper.PetStoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.star_pet_house_commons.constants.OperationFields;
import org.star_pet_house_commons.enums.CommonResultCode;
import org.star_pet_house_commons.enums.PetStoreStatus;
import org.star_pet_house_commons.model.LogApplication;
import org.star_pet_house_commons.model.PetStore;
import org.star_pet_house_service.services.IAdminServices;

import javax.xml.ws.Action;
import java.util.List;
import java.util.Map;

import static org.star_pet_house_commons.constants.OperationFields.RESULT;

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

    @Override
    public Map<String, Object> getStoreApplicationList(PetStore petStore, int page_size, int page_num) {
        Map<String, Object> result = super.getSuccessResultMap();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("store_status", PetStoreStatus.STORE_APPLICATION.getCode());
        Page<PetStore> petStorePage = (Page<PetStore>) petStoreMapper.selectPage(new Page<PetStore>(page_num, page_size), queryWrapper);
        List<PetStore> petStoreList = petStorePage.getRecords();
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
            logApplication.setApplication_no("0");
            logApplication.setApplication_info("审批通过");
        }else {
            //审批不通过
            queryPetStore.setStore_status(PetStoreStatus.STORE_APPLICATION_FAIL.getCode());
            logApplication.setApplication_no("-1");
            logApplication.setApplication_info("审批不通过");
        }
        petStoreMapper.updateById(queryPetStore);

        logApplication.setStore_id(queryPetStore.getStore_id());
        logApplication.setAccept_user_id(0);
        logApplicationMapper.insert(logApplication);
        return result;
    }
}