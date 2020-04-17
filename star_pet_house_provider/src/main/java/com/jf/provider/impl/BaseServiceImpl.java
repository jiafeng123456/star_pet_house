package com.jf.provider.impl;

import org.star_pet_house_commons.constants.OperationFields;
import org.star_pet_house_commons.enums.CommonResultCode;

import java.util.HashMap;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/13 0013 08:42
 */
public class BaseServiceImpl {

    /**
     * 获取返回成功父类结果集
     * @return
     */
    public Map<String, Object> getSuccessResultMap() {
        Map<String, Object> result = new HashMap<>();
        result.put(OperationFields.ERROR_NO, "0");
        result.put(OperationFields.SUCCESS, true);
        result.put(OperationFields.ERROR_INFO, CommonResultCode.SUCCESS.getDesc());
        return result;
    }
}
