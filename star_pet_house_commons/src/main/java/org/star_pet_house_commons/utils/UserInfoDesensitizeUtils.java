package org.star_pet_house_commons.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 12:44
 */
public class UserInfoDesensitizeUtils {
    /**
     * 手机号脱敏
     * @param mobile_tel
     * @return
     */
    public static String desensitizeMobile(String mobile_tel) {
        if(StringUtils.isNotBlank(mobile_tel) && mobile_tel.length()==11) {
            mobile_tel = mobile_tel.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        }
        return mobile_tel;
    }

    /** 
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     *  此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除1和4的任意数 
     * 17+除9的任意数 
     * 147 
     */
    public static boolean isChinaPhoneLegal(String str) {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     * @param str
     * @return
     */
    public static boolean isHKPhoneLegal(String str) {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
