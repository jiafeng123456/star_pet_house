package org.star_pet_house_commons.model;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;

import java.io.Serializable;

/*
 *@description:
 *@author jiafeng
 *@date 2020/1/22 0022 09:41
 */
@Data
public class User implements Serializable {

    private String user_id;

    private String user_name;

    private String password;

    private String phone;
}
