package org.star_pet_house_service.services;

import org.star_pet_house_commons.model.PetItem;
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

    public Map<String, Object> petOnShelves(PetItem petItem, String op_type);

    public Map<String, Object> petLowShelves(String pet_id);

    public Map<String, Object> getPetList(PetItem petItem, int page_size, int page_num);

    public Map<String, Object> getPetPaginator(PetItem petItem);

    public Map<String, Object> getStoreInfo(String user_name, String op_type);

}
