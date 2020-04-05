package org.star_pet_house_commons.error;

public enum EmBusinessError implements CommonError {

    //通用参数类型错误10001
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),

    UNKNOWN_ERROR(10002,"未知错误"),
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
