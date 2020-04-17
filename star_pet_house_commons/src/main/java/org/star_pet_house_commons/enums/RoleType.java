package org.star_pet_house_commons.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 21:03
 */
public enum RoleType {
    ADMIN_USER(1, "管理员"),

    NORMAL_USER(2, "普通用户"),

    MERCHANT_USER(3, "商家用户"),

    ;

    private final Integer code;
    private final String name;

    RoleType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByCode(String code) {
        String key = "";
        RoleType[] enumArr = RoleType.values();
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
        for (RoleType roleType : RoleType.values()){
            Map<String, Object> map = new HashMap<>();
            map.put("option", roleType.getName());
            map.put("value", roleType.getCode());
            cpCodeStatusList.add(map);
        }
        return cpCodeStatusList;
    }
}
