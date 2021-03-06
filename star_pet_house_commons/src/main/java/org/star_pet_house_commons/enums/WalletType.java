package org.star_pet_house_commons.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 21:24
 */
public enum WalletType {
    ADMIN_WALLET("1", "公共钱包"),

    NORMAL_WALLET("2", "用户钱包"),

    MERCHANT_WALLET("3", "商家钱包"),

    ;

    private final String code;
    private final String name;

    WalletType(String code, String name) {
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
        WalletType[] enumArr = WalletType.values();
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
        for (WalletType walletType : WalletType.values()){
            Map<String, Object> map = new HashMap<>();
            map.put("option", walletType.getName());
            map.put("value", walletType.getCode());
            cpCodeStatusList.add(map);
        }
        return cpCodeStatusList;
    }
}
