package org.star_pet_house_service.services;

import org.star_pet_house_commons.model.UserInfo;

import java.net.UnknownHostException;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/3/31 0031 17:07
 */
public interface IUserService {

    public Map<String, Object> userRegister(String userName, String password);

    public Map<String, Object> userLogin(String userName, String password) throws UnknownHostException;

    public Map<String, Object> modifyUserInfo(UserInfo userInfo);

    public Map<String, Object> getUserMenu(String username);

    public Map<String, Object> getUserInfo(String username);

    public Map<String, Object> changeStatus(String username);
}
