package org.star_pet_house_commons.error;

public enum EmBusinessError implements CommonError {

    //通用参数类型错误10001
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),

    UNKNOWN_ERROR(10002,"未知错误"),
    //20000开头为用户相关信息错误定义
    USER_NOT_EXITS(20001,"用户不存在"),

    USER_LOGIN_FAIL(20002,"用户名或密码不正确"),
    USER_NOT_LOGIN(20003,"用户名还未登录"),
    //30000为交易信息错误
    STCOK_NOT_ENOUGH(300001,"商品库存不足"),
    ;

    private EmBusinessError(int errCode,String errMsg){
        this.errCode=errCode;
        this.errMsg=errMsg;
    }

    private int errCode;
    private String errMsg;
    @Override
    public int getCode() {
        return this.errCode;
    }

    @Override
    public String getMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg=errMsg;
        return this;
    }
}
