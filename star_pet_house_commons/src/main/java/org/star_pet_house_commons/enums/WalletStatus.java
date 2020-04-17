package org.star_pet_house_commons.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 22:09
 */
public enum WalletStatus {

    WALLET_NORMAL("1", "正常"),

    WALLET_FREEZE("2", "冻结"),

    WALLET_LOGOUT("3", "注销"),

    ;

    private final String code;
    private final String name;

    WalletStatus(String code, String name) {
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
        WalletStatus[] enumArr = WalletStatus.values();
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
        for (WalletStatus walletStatus : WalletStatus.values()){
            Map<String, Object> map = new HashMap<>();
            map.put("option", walletStatus.getName());
            map.put("value", walletStatus.getCode());
            cpCodeStatusList.add(map);
        }
        return cpCodeStatusList;
    }
}
