package org.star_pet_house_commons.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "pet_fund")
public class PetFund implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_fund.id
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_fund.user_id
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private Integer user_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_fund.title
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_fund.author
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String author;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_fund.pet_type
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String pet_type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_fund.pet_name
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String pet_name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_fund.pet_image_url
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String pet_image_url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_fund.pet_description
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String pet_description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_fund.province_no
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String province_no;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_fund.city_no
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String city_no;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_fund.district_no
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String district_no;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_fund.address
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_fund.create_datetime
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private Date create_datetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_fund.pet_fund_status
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String pet_fund_status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pet_fund.contact_way
     *
     * @mbggenerated Mon Mar 30 18:38:12 CST 2020
     */
    private String contact_way;

}