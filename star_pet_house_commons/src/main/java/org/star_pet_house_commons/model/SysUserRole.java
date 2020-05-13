package org.star_pet_house_commons.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "sys_user_role")
public class SysUserRole implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_role.id
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_role.uid
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private Integer uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_role.role_id
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private Integer role_id;


}