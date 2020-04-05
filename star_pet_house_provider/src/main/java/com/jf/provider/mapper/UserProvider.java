package com.jf.provider.mapper;

/*
 *@description:
 *@author jiafeng
 *@date 2020/1/22 0022 09:47
 */
public class UserProvider {

    public String getUserMenu(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT  b.*,c.uid ");
        sql.append(" FROM sys_role_permission a");
        sql.append(" LEFT JOIN sys_permission b on a.permission_id = b.id ");
        sql.append(" LEFT JOIN sys_user_role c on a.role_id = c.role_id where 2>1 c.uid = '7'");
        return sql.toString();
    }
}
