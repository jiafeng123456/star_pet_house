package org.star_pet_house_service.services;

import org.star_pet_house_commons.model.PetStore;

import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/13 0013 09:30
 */
public interface IAdminServices {

    public Map<String, Object> getStoreApplicationList(PetStore petStore, int page_size, int page_num);

    public Map<String, Object> getStoreApplicationPaginator(PetStore petStore);

    public Map<String, Object> agreeApplication(String store_id, String op_type);

    public Map<String, Object> dealUser(String user_id,String op_type);

}
