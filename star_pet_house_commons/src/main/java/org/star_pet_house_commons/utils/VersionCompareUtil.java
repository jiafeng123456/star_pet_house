package org.star_pet_house_commons.utils;

/*
 *@description: 版本号比较 版本号类似 V1.0.0
 *@author jiafeng
 *@date 2020/11/19 0019 10:17
 */
public class VersionCompareUtil {

    public static void main(String[] args) {
        String lastVersion = "V2.1.1";
        String  version= "V3.1.1";
        if (compareVersion(lastVersion, version)){
            System.out.println("最新版本");
        }else {
            System.out.println("需要更新");
        }
    }


    public static boolean compareVersion(String oldVersion, String newVersion){
        String[] old_version_strs = oldVersion.replace("V", "").trim().split("\\.");
        String[] new_version_strs = newVersion.replace("V", "").trim().split("\\.");
        boolean is_new_version = true;
        for (int i = 0 ; i < old_version_strs.length; i++){
            if (Integer.valueOf(old_version_strs[i]) == Integer.valueOf(new_version_strs[i])){
                continue;
            }
            if (Integer.valueOf(old_version_strs[i]) > Integer.valueOf(new_version_strs[i])){
                is_new_version = false;
            }
            break;
        }
        return is_new_version;
    }
}
