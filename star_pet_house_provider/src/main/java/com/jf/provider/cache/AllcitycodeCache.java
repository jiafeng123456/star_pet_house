package com.jf.provider.cache;

import com.jf.provider.mapper.AllCityInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.star_pet_house_commons.cache.ICache;
import org.star_pet_house_commons.model.AllCityCode;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/13 0013 11:12
 */
@Component
public class AllcitycodeCache implements ICache<AllCityCode> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final long serialVersionUID = 1348949843808788355L;
    public static final String ID = "allcitycode";
    @Autowired
    private AllCityInfoMapper allCityInfoMapper;
    //key：省代码，value：省下面的城市列表
    private Map<String, List<AllCityCode>> groupByProvince = new HashMap<>();
    //key：市代码，value：市下面的所有区或者县列表
    private Map<String, List<AllCityCode>> groupByCity = new HashMap<>();
    //这个接口参数不用传，国家代码是写死的，返回中国所有省数据List
    private Map<String, List<AllCityCode>> provinceMap = new HashMap<>();
    Map<String, AllCityCode> configData = new HashMap<>();

    @Override
    @PostConstruct
    public void refresh() throws Exception {
        List<AllCityCode> allCityInfoList = allCityInfoMapper.selectList(null);
        if(null!=allCityInfoList && allCityInfoList.size()>0) {
            for(AllCityCode allcitycode : allCityInfoList) {
                //全部数据
                configData.put(allcitycode.getDistrict_no(), allcitycode);
                // 市级数据
                List<AllCityCode> cityList = groupByProvince.get(allcitycode.getProvince_no());
                // 区级数据
                List<AllCityCode> regionList = groupByCity.get(allcitycode.getCity_no());
                // 省级数据
                List<AllCityCode> provinceList = provinceMap.get(allcitycode.getProvince_no());

                if(StringUtils.equals("2", allcitycode.getDefault_level_no())){
                    if (cityList == null) {
                        cityList = new ArrayList<>();
                        groupByProvince.put(allcitycode.getProvince_no(), cityList);
                    }
                    cityList.add(allcitycode);
                }else if(StringUtils.equals("3", allcitycode.getDefault_level_no())){
                    if (regionList == null) {
                        regionList = new ArrayList<>();
                        groupByCity.put(allcitycode.getCity_no(), regionList);
                    }
                    regionList.add(allcitycode);
                }else if (StringUtils.equals("1", allcitycode.getDefault_level_no())){
                    if (provinceList == null) {
                        provinceList = new ArrayList<>();
                        provinceMap.put(allcitycode.getProvince_no(), provinceList);
                    }
                    provinceList.add(allcitycode);
                }
            }
        }
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Map<String, AllCityCode> getConfigData() {
        return configData;
    }

    /**
     * 获取按省份分区的城市代码列表
     */
    public Map<String, List<AllCityCode>> getProviceCityList() {
        return groupByProvince;
    }

    /**
     * 获取按省份分区的城市代码列表
     */
    public Map<String, List<AllCityCode>> getProviceList() {
        return provinceMap;
    }

    /**
     * 获取按市分区的区代码列表
     */
    public Map<String, List<AllCityCode>> getCityRegionList() {
        return groupByCity;
    }

    public String getCityNameByCityCode(String city_code) {
        List<AllCityCode> codeList = groupByCity.get(city_code);
        if (codeList != null && codeList.size() > 0) {
            return codeList.get(0).getCity_name();
        }
        logger.warn("city_code=" + city_code + "城市名字为空");
        return "";
    }

    public String getRegionNameByRegionCode(String region_code) {
        AllCityCode code = getConfigData().get(region_code);
        if (code != null) {
            return code.getCity_name();
        }
        logger.warn("region_code=" + region_code + "区名字为空");
        return "";
    }


    /**
     * 通过region_code查询城市信息
     */
    public AllCityCode getByCitiCode(String region_code) {
        return getConfigData().get(region_code);
    }

    public AllCityCode getByCityName(String provinceName, String cityName) {
        List<AllCityCode> allcitycodeList = new ArrayList<AllCityCode>(getConfigData().values());
        for (AllCityCode allcity : allcitycodeList) {
            if (StringUtils.equals("2", allcity.getDefault_level_no()) && provinceName.equals(allcity.getProvince_name()) && cityName.equals(allcity.getCity_name())) {
                return allcity;
            }
        }
        return null;
    }

    public AllCityCode getProviceCityByCode(String province_code, String city_code) {
        if (StringUtils.isNotBlank(province_code)) {
            List<AllCityCode> cityList = getProviceCityList().get(province_code);
            for (AllCityCode allcitycode : cityList) {
                if (allcitycode.getCity_no().equals(city_code)) {
                    return allcitycode;
                }
            }
        }
        return null;
    }
}
