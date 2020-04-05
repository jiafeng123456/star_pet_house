package org.star_pet_house_commons.enums;

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
        UserStatus[] enumArr = UserStatus.values();
        for (int i = 0; i < enumArr.length; i++) {
            if(enumArr[i].getCode().equals(code)) {
                key = enumArr[i].name();
                break;
            }
        }
        return key;
    }
}
