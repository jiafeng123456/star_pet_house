package com.jf.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jf.provider.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.star_pet_house_commons.constants.OperationFields;
import org.star_pet_house_commons.enums.*;
import org.star_pet_house_commons.model.*;
import org.star_pet_house_commons.model.response.PermissionEnumVO;
import org.star_pet_house_commons.utils.MD5Util;
import org.star_pet_house_service.services.IUserService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.star_pet_house_commons.constants.OperationFields.*;

/*
 *@description:
 *@author jiafeng
 *@date 2020/3/31 0031 17:11
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private LogLoginMapper logLoginMapper;
    @Autowired
    private LogWalletMapper logWalletMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> userRegister(String userName, String password) {
        Map<String, Object> result = super.getSuccessResultMap();

        QueryWrapper queryWrapper = new QueryWrapper<SysUser>();
        queryWrapper.eq("user_name", userName);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if (sysUser != null){
            result.put(SUCCESS, false);
            result.put(ERROR_NO, CommonResultCode.USERNAME_HAS_EXIST.getCode());
            result.put(ERROR_INFO, CommonResultCode.USERNAME_HAS_EXIST.getDesc());
            return result;
        }
        //保存用户
        Map<String, String> md5Pass = MD5Util.generate(password);
        SysUser sysUserInsert = new SysUser();
        sysUserInsert.setUser_name(userName);
        sysUserInsert.setSalt(md5Pass.get("salt"));
        sysUserInsert.setPassword(md5Pass.get("password"));
        sysUserInsert.setStatus(UserStatus.USER_NORMAL.getCode());
        sysUserMapper.insert(sysUserInsert);

        //保存用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_name(sysUserInsert.getUser_name());
        userInfo.setUser_id(sysUserInsert.getUid());
        userInfo.setStatus("0");//0未设置用户信息 修改用户信息后改为1
        userInfoMapper.insert(userInfo);

        //设置用户权限
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUid(sysUserInsert.getUid());
        sysUserRole.setRole_id(RoleType.NORMAL_USER.getCode());
        sysUserRoleMapper.insert(sysUserRole);

        //初始化钱包
        Wallet wallet = new Wallet();
        wallet.setUser_id(sysUserInsert.getUid());
        wallet.setAccount_balance(0L);
        wallet.setWallet_status(WalletStatus.WALLET_NORMAL.getCode());
        wallet.setWallet_type(WalletType.NORMAL_WALLET.getCode());
        walletMapper.insert(wallet);

        //钱包初始化日志
        LogWallet logWallet = new LogWallet();
        logWallet.setError_no("0");
        logWallet.setError_info("钱包初始化");
        logWallet.setWallet_id(wallet.getWallet_id());
        logWallet.setMoney(0L);
        logWallet.setTitle("钱包初始化");
        logWalletMapper.insert(logWallet);

        return result;
    }

    @Override
    public Map<String, Object> userLogin(String userName, String password) throws UnknownHostException {
        Map<String, Object> result = super.getSuccessResultMap();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",userName);
        String addr = InetAddress.getLocalHost().getHostAddress();
        LogLogin logLogin = new LogLogin();
        logLogin.setNick_name(userName);
        logLogin.setIp_address(addr);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if (sysUser == null){
            result.put(SUCCESS, false);
            result.put(ERROR_NO, CommonResultCode.USER_NOT_EXIST.getCode());
            result.put(ERROR_INFO, CommonResultCode.USER_NOT_EXIST.getDesc());
            logLogin.setError_no(CommonResultCode.USER_NOT_EXIST.getCode());
            logLogin.setError_info(CommonResultCode.USER_NOT_EXIST.getDesc());
            logLoginMapper.insert(logLogin);
            return result;
        }
        logLogin.setUser_id(sysUser.getUid());
        Boolean passFlag = MD5Util.verify(password, sysUser.getPassword());
        if (!passFlag){
            result.put(SUCCESS, false);
            result.put(ERROR_NO, CommonResultCode.USER_PASSWORD_NOT_TRUE.getCode());
            result.put(ERROR_INFO, CommonResultCode.USER_PASSWORD_NOT_TRUE.getDesc());
            logLogin.setError_no(CommonResultCode.USER_PASSWORD_NOT_TRUE.getCode());
            logLogin.setError_info(CommonResultCode.USER_PASSWORD_NOT_TRUE.getDesc());
            logLoginMapper.insert(logLogin);
            return result;
        }
        logLogin.setError_no(CommonResultCode.SUCCESS.getCode());
        logLogin.setError_info(CommonResultCode.SUCCESS.getDesc());
        logLoginMapper.insert(logLogin);
        return result;
    }

    @Override
    public Map<String, Object> modifyUserInfo(UserInfo userInfo) {
        Map<String, Object> result = super.getSuccessResultMap();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userInfo.getUser_id());
        queryWrapper.eq("user_name",userInfo.getUser_name());
        UserInfo userInfoQuery = userInfoMapper.selectOne(queryWrapper);
        if (userInfo == null) {
            result.put(SUCCESS, false);
            result.put(ERROR_NO, CommonResultCode.USER_NOT_EXIST.getCode());
            result.put(ERROR_INFO, CommonResultCode.USER_NOT_EXIST.getDesc());
            return result;
        }
        userInfo.setUpdate_datetime(new Date());
        userInfo.setStatus("1");
        userInfoMapper.update(userInfo, queryWrapper);
        return result;
    }

    @Override
    public Map<String, Object> getUserMenu(String userId) {
        Map<String, Object> result = super.getSuccessResultMap();
        //TODO 关联表查询用户权限
        List<PermissionEnumVO> permissionEnumVOList = userMapper.getUserMenu(userId);
        result.put(OperationFields.RESULT, permissionEnumVOList);
        return result;
    }
}
