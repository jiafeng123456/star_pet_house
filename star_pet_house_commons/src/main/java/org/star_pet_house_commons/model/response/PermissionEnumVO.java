package org.star_pet_house_commons.model.response;

import lombok.Data;

import java.io.Serializable;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/13 0013 10:01
 */
@Data
public class PermissionEnumVO implements Serializable {

    private String id;

    private String name;

    private String permission;

    private String url;
}
