package org.star_pet_house_service.services;

import org.star_pet_house_commons.model.PetStore;

import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/4/5 0005 17:58
 */
public interface IPetStoreService {

    public Map<String, Object> saveStoreApplication(PetStore petStore, String op_type);

    public Map<String, Object> getStoreList(PetStore petStore, int page_size, int page_num);

    public Map<String, Object> getStorePaginator(PetStore petStore);
}
