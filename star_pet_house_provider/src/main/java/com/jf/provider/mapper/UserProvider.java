package com.jf.provider.mapper;

import org.apache.commons.lang3.StringUtils;

/*
 *@description:
 *@author jiafeng
 *@date 2020/1/22 0022 09:47
 */
public class UserProvider {

    public String getUserMenu(String userId){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT  b.*,c.uid ");
        sql.append(" FROM sys_role_permission a ");
        sql.append(" LEFT JOIN sys_permission b on a.permission_id = b.id ");
        sql.append(" LEFT JOIN sys_user_role c on a.role_id = c.role_id where 2>1 ");
        if (StringUtils.isNotBlank(userId)){
            sql.append(" and uid = " + userId);
        }
        sql.append(" order by sort_flag asc ");
        return sql.toString();
    }
}
