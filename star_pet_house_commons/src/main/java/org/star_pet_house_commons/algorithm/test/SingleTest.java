package org.star_pet_house_commons.algorithm.test;

/*
 *@description:
 *@author jiafeng
 *@date 2020/12/22 0022 12:40
 */
public class SingleTest {

    public static SingleTest instance = new SingleTest();

    public SingleTest(){};
    public static SingleTest getInstance(){
        return instance;
    }
}
