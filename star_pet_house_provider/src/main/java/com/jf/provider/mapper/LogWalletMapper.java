package com.jf.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.star_pet_house_commons.model.LogWallet;
import org.star_pet_house_commons.model.response.LogLoginVO;
import org.star_pet_house_commons.model.response.LogWalletVO;

import java.util.List;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 21:43
 */
public interface LogWalletMapper extends BaseMapper<LogWallet> {

    @SelectProvider(method = "queryPageList",type = LogWalletProvider.class)
    List<LogWalletVO> queryPageList(Page<LogWalletVO> page, @Param("logWalletVO") LogWalletVO logWalletVO,@Param("star_date") String star_date,@Param("end_date") String end_date);
}
