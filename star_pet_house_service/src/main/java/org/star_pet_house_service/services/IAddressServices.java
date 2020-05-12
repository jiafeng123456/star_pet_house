package org.star_pet_house_service.services;

import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/5/4 0004 22:27
 */
public interface IAddressServices {

    public Map<String, Object> queryByParentId();

    public Map<String, Object> queryCityByParentId(String province_no);

    public Map<String, Object> queryDistriceByParentId(String city_no);

}
