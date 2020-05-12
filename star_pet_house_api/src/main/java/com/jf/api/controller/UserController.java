package com.jf.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.star_pet_house_commons.model.UserInfo;
import org.star_pet_house_service.services.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.UnknownHostException;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/3/31 0031 17:05
 */
@CrossOrigin
@RestController
@Api(value = "用户接口", tags = "用户接口")
@RequestMapping("user")
public class UserController{

    @Reference
    private IUserService userService;

    @ApiOperation("登录")
    @RequestMapping("/login")
    public Map<String,Object> userLogin(String username, String password) throws UnknownHostException {
        return userService.userLogin(username, password);
    }

    @ApiOperation("注册")
    @RequestMapping("/register")
    public Map<String,Object> userRegister(String username, String password){
        return userService.userRegister(username, password);
    }

    @ApiOperation("修改用户信息")
    @RequestMapping("/modifyUserInfo")
    public Map<String, Object> modifyUserInfo(UserInfo userInfo){
        return userService.modifyUserInfo(userInfo);
    }

    @ApiOperation("获取用户信息")
    @RequestMapping("/getUserInfo")
    public Map<String, Object> getUserInfo(String username){
        return userService.getUserInfo(username);
    }

    @ApiOperation("用户菜单")
    @RequestMapping("/getUserMenu")
    public Map<String, Object> getUserMenu(String username){
        return userService.getUserMenu(username);
    }

    @ApiOperation("自行完善状态")
    @RequestMapping("/changeStatus")
    public Map<String, Object> changeStatus(String username){
        return userService.changeStatus(username);
    }
}
