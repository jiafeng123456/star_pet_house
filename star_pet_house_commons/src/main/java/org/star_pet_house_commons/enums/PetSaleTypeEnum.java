package org.star_pet_house_commons.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/13 0013 18:49
 */
public enum PetSaleTypeEnum {
    PET_ITEM_SALE("0", "宠物出售"),
    PET_ITEM_SEND("1", "宠物赠送"),
            ;

    private final String code;
    private final String name;

    PetSaleTypeEnum(String code, String name) {
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
        PetSaleTypeEnum[] enumArr = PetSaleTypeEnum.values();
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
        for (PetSaleTypeEnum petStatusEnum : PetSaleTypeEnum.values()){
            Map<String, Object> map = new HashMap<>();
            map.put("option", petStatusEnum.getName());
            map.put("value", petStatusEnum.getCode());
            cpCodeStatusList.add(map);
        }
        return cpCodeStatusList;
    }
}
