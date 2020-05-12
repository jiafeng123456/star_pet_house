package com.jf.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jf.provider.cache.AllcitycodeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.star_pet_house_commons.model.AllCityCode;
import org.star_pet_house_service.services.IAddressServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/5/4 0004 22:27
 */
@Service
public class AddressServicesImpl extends BaseServiceImpl implements IAddressServices {

    @Autowired
    private AllcitycodeCache allcitycodeCache;

    @Override
    public Map<String, Object> queryByParentId() {
        Map<String, Object> result = super.getSuccessResultMap();
        Map<String, List<AllCityCode>> proviceList = allcitycodeCache.getProviceList();
        List<AllCityCode> allCityCodeList = new ArrayList<>();
        for (String key : proviceList.keySet()){
            allCityCodeList.add(proviceList.get(key).get(0));
        }
        result.put("allCityCodeList",allCityCodeList);
        return result;
    }

    @Override
    public Map<String, Object> queryCityByParentId(String province_no) {
        Map<String, Object> result = super.getSuccessResultMap();
        Map<String, List<AllCityCode>> cityList = allcitycodeCache.getProviceCityList();
        List<AllCityCode> allCityCodeList = cityList.get(province_no);
        result.put("allCityCodeList",allCityCodeList);
        return result;
    }

    @Override
    public Map<String, Object> queryDistriceByParentId(String city_no) {
        Map<String, Object> result = super.getSuccessResultMap();
        Map<String, List<AllCityCode>> regionList = allcitycodeCache.getCityRegionList();
        List<AllCityCode> allCityCodeList = regionList.get(city_no);
        result.put("allCityCodeList",allCityCodeList);
        return result;
    }
}
