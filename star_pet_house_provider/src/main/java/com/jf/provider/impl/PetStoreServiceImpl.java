package com.jf.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jf.provider.mapper.PetItemMapper;
import com.jf.provider.mapper.PetStoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.star_pet_house_commons.enums.CommonResultCode;
import org.star_pet_house_commons.enums.PetSaleTypeEnum;
import org.star_pet_house_commons.enums.PetStatusEnum;
import org.star_pet_house_commons.enums.PetStoreStatus;
import org.star_pet_house_commons.model.PetItem;
import org.star_pet_house_commons.model.PetStore;
import org.star_pet_house_service.services.IPetStoreService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.star_pet_house_commons.constants.OperationFields.*;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/5 0005 18:01
 */
@Service
public class PetStoreServiceImpl extends BaseServiceImpl implements IPetStoreService {

    @Autowired
    private PetStoreMapper petStoreMapper;
    @Autowired
    private PetItemMapper petItemMapper;

    @Override
    public Map<String, Object> saveStoreApplication(PetStore petStore, String op_type) {
        //op_type = 0 为申请，  = 1 为修改
        Map<String, Object> result = super.getSuccessResultMap();
        if (op_type.equals("0")){
           if (petStore.getStore_id() != null){
               result.put(SUCCESS, false);
               result.put(ERROR_NO, CommonResultCode.STORE_HAS_EXIST.getCode());
               result.put(ERROR_INFO, CommonResultCode.STORE_HAS_EXIST.getDesc());
               return result;
           }
           petStore.setStore_score(100L);
           petStore.setStore_status(PetStoreStatus.STORE_APPLICATION.getCode());
           petStoreMapper.insert(petStore);
        }else if (op_type.equals("1")){
            PetStore petStoreQuery = petStoreMapper.selectById(petStore.getStore_id());
            if (petStoreQuery == null){
                result.put(SUCCESS, false);
                result.put(ERROR_NO, CommonResultCode.STORE_NOT_EXIST.getCode());
                result.put(ERROR_INFO, CommonResultCode.STORE_NOT_EXIST.getDesc());
                return result;
            }
            petStoreMapper.updateById(petStore);
        }
        return result;
    }

    @Override
    public Map<String, Object> getStoreList(PetStore petStore, int page_size, int page_num) {
        Map<String, Object> result = super.getSuccessResultMap();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("store_status", PetStoreStatus.STORE_NORMAL.getCode());
        Page<PetStore> petStorePage = (Page<PetStore>) petStoreMapper.selectPage(new Page<PetStore>(page_num, page_size), queryWrapper);        List<PetStore> petStoreList = petStorePage.getRecords();
        result.put(RESULT,petStoreList);
        return result;
    }

    @Override
    public Map<String, Object> getStorePaginator(PetStore petStore) {
        Map<String, Object> result = super.getSuccessResultMap();
        QueryWrapper queryWrapper = new QueryWrapper(petStore);
        queryWrapper.eq("store_status", PetStoreStatus.STORE_NORMAL.getCode());
        int total = petStoreMapper.selectCount(queryWrapper);
        result.put("total", total);
        return result;
    }

    @Override
    public Map<String, Object> petOnShelves(PetItem petItem, String op_type) {
        Map<String, Object> result = super.getSuccessResultMap();
        if (op_type.equals("0")){
            //交易
            petItem.setPet_status(PetStatusEnum.NORMAL.getCode());
            petItem.setSale_type(PetSaleTypeEnum.PET_ITEM_SALE.getCode());
        }else {
            //赠送
            petItem.setPet_status(PetStatusEnum.NORMAL.getCode());
            petItem.setSale_type(PetSaleTypeEnum.PET_ITEM_SEND.getCode());
        }
        petItemMapper.insert(petItem);
        return result;
    }
}
