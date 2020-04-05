package org.star_pet_house_commons.utils;

import org.apache.commons.lang3.StringUtils;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 12:32
 */
public class MysqlSqlProviderUtil {

    /***
     * 针对mysql的分页修改
     * @return
     */

    public static String getPaginationSql(String querySql) {
        return " select * from ( "+ querySql + ") a  limit  #{start},#{page_size}";
    }

    public static String getPaginationSql2(String querySql) {
        String mysqlQuerySql="select * from ( "+querySql.toUpperCase().replace("WHERE 1=1",",(Select (@rowNum :=0) ) b) res where rowNo>#{start} and rowNo<#{end}").replace("FROM",",(@rowNum:=@rowNum+1) as rowNo FROM ");
        return mysqlQuerySql;
    }


    public static String getCountSql(String querySql) {
        return "SELECT count(1) FROM (" + querySql + ") cnt";
    }

    /**
     * 判断包含关系，不区分大小写
     * @param array
     * @param value
     * @return
     */
    private static boolean containsIgnoreCase(String[] array, String value) {
        if (array == null || array.length == 0) {
            return false;
        }
        for (String v : array) {
            if (StringUtils.equalsIgnoreCase(v, value)) {
                return true;
            }
        }
        return false;
    }

    private static void dealSysDateParam4Setter(String k, SysDate v, StringBuffer buff) {
        // 日期类型特殊处理
        if (v.isNull()) {
            buff.append(k).append("=null, ");
        } else {
            buff.append(k).append("=now(), ");
        }
    }

    private static void dealSysDateParam4Insert(SysDate v, StringBuffer buff) {
        // 日期类型特殊处理
        if (v.isNull()) {
            buff.append("null,");
        } else {
            buff.append("now(),");
        }
    }
}
