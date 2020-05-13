package com.jf.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.commons.lang3.StringUtils;
import org.star_pet_house_commons.model.LogLogin;
import org.star_pet_house_commons.model.response.LogLoginVO;

import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 21:34
 */
public class LogLoginProvider {
    public String queryPageList(Map<String, Object> map){
        StringBuilder sql = new StringBuilder();
        LogLoginVO logLoginVO = (LogLoginVO) map.get("logLoginVO");
        String star_date = map.get("star_date") == null ? " " : map.get("star_date").toString();
        String end_date = map.get("end_date") == null ? " " : map.get("end_date").toString();
        sql.append("SELECT ll.*,ui.name FROM log_login ll LEFT JOIN user_info ui ON ll.user_id = ui.user_id where 2>1 ");
        if (StringUtils.isNotBlank(logLoginVO.getUser_id())){
            sql.append(" and ll.user_id = '" + logLoginVO.getUser_id() + "' " );
        }
        if (StringUtils.isNotBlank(star_date)){
            sql.append(" and ll.login_datetime >= DATE_FORMAT('" + star_date + "','%Y-%m-%d %H:%i:%S') ");
        }
        if (StringUtils.isNotBlank(end_date)){
            sql.append(" and ll.login_datetime <= DATE_FORMAT('" + end_date + "','%Y-%m-%d %H:%i:%S') ");
        }

        sql.append(" order by ll.occur_datetime desc ");
        return sql.toString();
    }
}
