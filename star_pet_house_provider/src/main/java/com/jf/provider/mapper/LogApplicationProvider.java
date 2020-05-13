package com.jf.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.star_pet_house_commons.model.LogApplication;
import org.star_pet_house_commons.model.response.LogApplicationVO;

import java.util.List;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/13 0013 18:35
 */
public class LogApplicationProvider {

    public String queryPageList(Map<String, Object> map){
        StringBuilder sql = new StringBuilder();
        LogApplicationVO logApplicationVO = (LogApplicationVO)map.get("logApplicationVO");
        sql.append("SELECT la.*,ui.user_name,ps.store_name FROM log_application la LEFT JOIN user_info ui ON la.accept_user_id = ui.user_id LEFT JOIN pet_store ps ON la.store_id = ps.store_id where 2>1 ");
        if (StringUtils.isNotBlank(logApplicationVO.getUser_name())){
            sql.append(" and ui.user_name like '%" + logApplicationVO.getUser_name() + "%' ");
        }
        if (StringUtils.isNotBlank(logApplicationVO.getStore_id())){
            sql.append(" and la.store_id = '" + logApplicationVO.getStore_id() + "' ");
        }
        sql.append(" order by la.create_datetime desc ");
        return sql.toString();
    }
}
