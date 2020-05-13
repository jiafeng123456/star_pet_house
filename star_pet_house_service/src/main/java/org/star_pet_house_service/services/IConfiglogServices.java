package org.star_pet_house_service.services;

import org.springframework.web.bind.annotation.RequestParam;
import org.star_pet_house_commons.model.*;
import org.star_pet_house_commons.model.response.*;

import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/5/12 0012 23:47
 */
public interface IConfiglogServices {

    public Map<String, Object> getApplicationlog(LogApplicationVO logApplicationVO, @RequestParam(defaultValue = "10") int page_size,
                                                 @RequestParam(defaultValue = "0") int page_num);

    public Map<String, Object> getLoginlog(LogLoginVO logLoginVO, @RequestParam(defaultValue = "10") int page_size,
                                           @RequestParam(defaultValue = "0") int page_num,String date_time);

    public Map<String, Object> getWalletlog(LogWalletVO logWalletVO, @RequestParam(defaultValue = "10") int page_size,
                                            @RequestParam(defaultValue = "0") int page_num,String date_time);

    public Map<String, Object> getComplainlog(LogComplainVO logComplainVO, @RequestParam(defaultValue = "10") int page_size,
                                              @RequestParam(defaultValue = "0") int page_num,String date_time);
    public Map<String, Object> getUserinfoList(UserInfoVO userInfoVO, @RequestParam(defaultValue = "10") int page_size,
                                               @RequestParam(defaultValue = "0") int page_num, String date_time);
}
