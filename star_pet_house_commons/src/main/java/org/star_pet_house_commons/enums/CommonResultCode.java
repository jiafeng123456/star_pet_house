package org.star_pet_house_commons.enums;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 14:58
 */
public enum  CommonResultCode {

    SUCCESS("0", "处理成功", "处理成功"),

    //用户相关异常码10000-10999
    USERNAME_HAS_EXIST("10000", "用户名已存在", "用户名已存在"),
    USER_NOT_EXIST("10001", "用户不存在", "用户不存在"),
    USER_PASSWORD_NOT_TRUE("10002", "用户密码不正确", "用户密码不正确"),

    //店铺相关异常码20000-20999
    STORE_HAS_EXIST("20000", "店铺已存在", "店铺已存在"),
    STORE_NOT_EXIST("20001", "店铺不存在", "店铺不存在"),

    ;

    /** 错误码 */
    private String code;

    /** 描述 */
    private String desc;

    /** 显示错误内容 */
    private String view;

    /**
     * 构造函数
     *
     * @param code 错误码
     * @param desc 描述
     * @param view 显示错误内容
     */
    private CommonResultCode(String code, String desc, String view) {
        this.code = code;
        this.desc = desc;
        this.view = view;
    }

    /**
     * 通过name获取结果码
     *
     * @param code 错误码
     * @return 返回业务结果码
     */
    public static CommonResultCode getResultEnum(String code) {
        for (CommonResultCode result : values()) {
            if (result.getCode().equalsIgnoreCase(code)) {
                return result;
            }
        }
        return null;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getView() {
        return view;
    }


    public void setView(String view) {
        this.view = view;
    }
}
