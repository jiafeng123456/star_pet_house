package org.star_pet_house_commons.utiltest;

import java.util.TreeSet;
import java.util.*;
/*
 *@description:
 *@author jiafeng
 *@date 2020/12/8 0008 11:05
 */
public class DemoTest {

    public static void main(String[] args) {
        B b = (B)aTest();
        System.out.println(b.getB());
    }

    public static A aTest(){
        B b = new B();
        b.setA("a");
        b.setB("b");
        return b;
    }
}
