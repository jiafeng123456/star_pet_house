package com.jf.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.commons.lang3.StringUtils;
import org.star_pet_house_commons.model.LogComplain;
import org.star_pet_house_commons.model.response.LogComplainVO;

import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 21:43
 */
public class LogComplainProvider {

    public String queryPageList(Map<String, Object> map){
        StringBuilder sql = new StringBuilder();
        LogComplainVO logComplainVO = (LogComplainVO) map.get("logComplainVO");
        String star_date = map.get("star_date") == null ? " " : map.get("star_date").toString();
        String end_date = map.get("end_date") == null ? " " : map.get("end_date").toString();
        sql.append("SELECT lc.*,ui.user_name,ui.name,ps.store_name,po.title FROM log_complain lc LEFT JOIN user_info ui ON lc.user_id = ui.user_id LEFT JOIN pet_store ps ON ps.store_id = lc.store_id LEFT JOIN pet_order po ON po.order_no = lc.sorder_no where 2>1 ");
        if (StringUtils.isNotBlank(logComplainVO.getStore_id())){
            sql.append(" and lc.store_id = '" + logComplainVO.getStore_id() + "' " );
        }
        if (StringUtils.isNotBlank(logComplainVO.getUser_id())){
            sql.append(" and lc.user_id = '" + logComplainVO.getUser_id() + "' " );
        }
        if (StringUtils.isNotBlank(logComplainVO.getSorder_no())){
            sql.append(" and lc.sorder_no = '" + logComplainVO.getSorder_no() + "' " );
        }
        if (StringUtils.isNotBlank(star_date)){
            sql.append(" and lc.create_datetime >= DATE_FORMAT('" + star_date + "','%Y-%m-%d %H:%i:%S') ");
        }
        if (StringUtils.isNotBlank(end_date)){
            sql.append(" and lc.create_datetime <= DATE_FORMAT('" + end_date + "','%Y-%m-%d %H:%i:%S') ");
        }
        sql.append(" order by lc.create_datetime desc ");
        return sql.toString();
    }
}
