package org.star_pet_house_commons.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/5 0005 18:07
 */
public enum PetStoreStatus {
    STORE_APPLICATION("0", "店铺申请"),

    STORE_NORMAL("1", "店铺正常"),

    STORE_FREEZE("2", "店铺冻结"),

    STORE_REORGANIZATION("3", "店铺整顿"),

    STORE_APPLICATION_FAIL("4", "申请失败"),

    ;

    private final String code;
    private final String name;

    PetStoreStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByCode(String code) {
        String key = "";
        PetStoreStatus[] enumArr = PetStoreStatus.values();
        for (int i = 0; i < enumArr.length; i++) {
            if(enumArr[i].getCode().equals(code)) {
                key = enumArr[i].name();
                break;
            }
        }
        return key;
    }

    public static List<Map<String, Object>> getEnumsList(){
        List<Map<String, Object>> cpCodeStatusList = new ArrayList<>();
        for (PetStoreStatus petStoreStatus : PetStoreStatus.values()){
            Map<String, Object> map = new HashMap<>();
            map.put("option", petStoreStatus.getName());
            map.put("value", petStoreStatus.getCode());
            cpCodeStatusList.add(map);
        }
        return cpCodeStatusList;
    }
}
