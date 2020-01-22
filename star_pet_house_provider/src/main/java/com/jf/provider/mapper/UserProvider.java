package com.jf.provider.mapper;

/*
 *@description:
 *@author jiafeng
 *@date 2020/1/22 0022 09:47
 */
public class UserProvider {

    public String queryAll(){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_user");
        return sql.toString();
    }
}
