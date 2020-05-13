package com.jf.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.star_pet_house_commons.model.UserInfo;
import org.star_pet_house_commons.model.response.LogComplainVO;
import org.star_pet_house_commons.model.response.LogWalletVO;
import org.star_pet_house_commons.model.response.UserInfoVO;

import java.util.List;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 16:42
 */
public class UserInfoProvider{

    public String queryPageList(Map<String, Object> map){
        StringBuilder sql = new StringBuilder();
        UserInfoVO userInfoVO = (UserInfoVO) map.get("userInfoVO");
        String star_date = map.get("star_date") == null ? " " : map.get("star_date").toString();
        String end_date = map.get("end_date") == null ? " " : map.get("end_date").toString();
        sql.append("SELECT ui.*,su.status as user_status FROM user_info ui LEFT JOIN sys_user su ON ui.user_id = su.uid where 2>1 ");
        if (StringUtils.isNotBlank(userInfoVO.getUser_gender())){
            sql.append(" and ui.user_gender = '" + userInfoVO.getUser_gender()+ "' ");
        }
        if (StringUtils.isNotBlank(userInfoVO.getUser_status())){
            sql.append(" and su.status = '" + userInfoVO.getUser_status()+ "' ");
        }
        String user_id = userInfoVO.getUser_id() == null ? " " : userInfoVO.getUser_id().toString();
        if (StringUtils.isNotBlank(user_id)){
            sql.append(" and ui.user_id = '" + user_id + "' ");
        }
        if (StringUtils.isNotBlank(star_date)){
            sql.append(" and ui.create_datetime >= DATE_FORMAT('" + star_date + "','%Y-%m-%d %H:%i:%S') ");
        }
        if (StringUtils.isNotBlank(end_date)){
            sql.append(" and ui.create_datetime <= DATE_FORMAT('" + end_date + "','%Y-%m-%d %H:%i:%S') ");
        }
        sql.append(" order by ui.create_datetime desc ");
        return sql.toString();
    }
}
