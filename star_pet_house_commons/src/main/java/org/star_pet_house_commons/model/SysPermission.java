package org.star_pet_house_commons.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "sys_permission")
public class SysPermission {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_permission.id
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_permission.available
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String available;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_permission.name
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_permission.parent_id
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String parent_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_permission.parent_ids
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String parent_ids;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_permission.permission
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String permission;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_permission.resource_type
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String resource_type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_permission.url
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String url;

}