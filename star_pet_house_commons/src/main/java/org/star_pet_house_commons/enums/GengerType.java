package org.star_pet_house_commons.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 22:07
 */
public enum GengerType {

    BOY("0", "男"),

    GRIL("1", "女"),
            ;

    private final String code;
    private final String name;

    GengerType(String code, String name) {
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
        GengerType[] enumArr = GengerType.values();
        for (int i = 0; i < enumArr.length; i++) {
            if(enumArr[i].getCode().equals(code)) {
                key = enumArr[i].getName();
                break;
            }
        }
        return key;
    }

    public static List<Map<String, Object>> getEnumsList(){
        List<Map<String, Object>> cpCodeStatusList = new ArrayList<>();
        for (GengerType gengerType : GengerType.values()){
            Map<String, Object> map = new HashMap<>();
            map.put("option", gengerType.getName());
            map.put("value", gengerType.getCode());
            cpCodeStatusList.add(map);
        }
        return cpCodeStatusList;
    }
}
