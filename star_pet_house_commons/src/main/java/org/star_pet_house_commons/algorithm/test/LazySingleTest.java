package org.star_pet_house_commons.algorithm.test;

/*
 *@description:
 *@author jiafeng
 *@date 2020/12/22 0022 12:43
 */
public class LazySingleTest {
    public static LazySingleTest instance = null;

    public LazySingleTest(){};

    public static LazySingleTest getInstance(){
        if (instance == null){
            instance = new LazySingleTest();
        }
        return instance;
    }
}
