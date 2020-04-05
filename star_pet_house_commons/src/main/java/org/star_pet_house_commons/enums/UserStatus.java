package org.star_pet_house_commons.enums;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 19:18
 */
public enum  UserStatus {
    USER_NORMAL(0, "用户正常"),

    USER_FREEZE(1, "用户冻结"),

    USER_LOGOUT(2, "用户注销"),

    ;

    private final Integer code;
    private final String name;

    UserStatus(Integer code, String name) {
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
