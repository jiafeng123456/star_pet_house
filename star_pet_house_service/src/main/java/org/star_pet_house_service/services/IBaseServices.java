package org.star_pet_house_service.services;

import javax.servlet.http.HttpServletRequest;

/*
 *@description:
 *@author jiafeng
 *@date 2020/3/19 0019 18:43
 */
public interface IBaseServices {
    public Object handlerException(HttpServletRequest request, Exception ex);
}
