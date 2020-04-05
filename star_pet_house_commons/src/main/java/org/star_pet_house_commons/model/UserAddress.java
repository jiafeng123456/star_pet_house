package org.star_pet_house_commons.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "user_address")
public class UserAddress {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.address_id
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    @TableId(value = "address_id")
    private Integer address_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.address_name
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String address_name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.address_type
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String address_type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.address_status
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String address_status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.address_zipcode
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String address_zipcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.user_id
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private Integer user_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.mobile_tel
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String mobile_tel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.consignee
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String consignee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.is_default
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String is_default;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.create_datetime
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private Date create_datetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.update_datetime
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private Date update_datetime;

}