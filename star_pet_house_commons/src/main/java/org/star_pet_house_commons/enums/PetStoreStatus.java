package org.star_pet_house_commons.enums;

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
