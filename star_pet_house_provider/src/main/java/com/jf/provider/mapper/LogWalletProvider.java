package com.jf.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.commons.lang3.StringUtils;
import org.star_pet_house_commons.model.LogWallet;
import org.star_pet_house_commons.model.response.LogComplainVO;
import org.star_pet_house_commons.model.response.LogWalletVO;

import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 21:43
 */
public class LogWalletProvider {

    public String queryPageList(Map<String, Object> map){
        StringBuilder sql = new StringBuilder();
        LogWalletVO logWalletVO = (LogWalletVO) map.get("logWalletVO");
        String star_date = map.get("star_date") == null ? " " : map.get("star_date").toString();
        String end_date = map.get("end_date") == null ? " " : map.get("end_date").toString();
        sql.append("SELECT lw.*,ui.user_name as from_user_name,ui.name as from_name,ui2.user_name as to_user_name,ui2.name as to_name FROM log_wallet lw LEFT JOIN user_info ui ON ui.user_id = lw.from_user_id LEFT JOIN user_info ui2 ON ui2.user_id = lw.to_user_id where 2>1 ");
        if (StringUtils.isNotBlank(logWalletVO.getSorder_no())){
            sql.append(" and lw.sorder_no = '" + logWalletVO.getSorder_no() + "' " );
        }
        if (StringUtils.isNotBlank(logWalletVO.getWallet_id())){
            sql.append(" and lw.wallet_id = '" + logWalletVO.getWallet_id() + "' " );
        }
        if (StringUtils.isNotBlank(star_date)){
            sql.append(" and lw.create_datetime >= DATE_FORMAT('" + star_date + "','%Y-%m-%d %H:%i:%S') ");
        }
        if (StringUtils.isNotBlank(end_date)){
            sql.append(" and lw.create_datetime <= DATE_FORMAT('" + end_date + "','%Y-%m-%d %H:%i:%S') ");
        }
        sql.append(" order by lw.create_datetime desc ");
        return sql.toString();
    }
}
