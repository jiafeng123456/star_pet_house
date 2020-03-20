package org.star_pet_house_commons.error;

public interface CommonError {
    public int getCode();
    public String getMsg();
    public CommonError setErrMsg(String errMsg);
}
