package org.star_pet_house_commons.algorithm.test;

import java.util.*;

/*
 *@description:
 *@author jiafeng
 *@date 2020/12/12 0012 19:28
 */
public class demo1 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String str = sc.nextLine();
            String[] strArr = str.split("");
            Map<String, Integer> map = new HashMap<>();
            for (String a : strArr) {
                if (map.containsKey(a)){
                    map.put(a, map.get(a) +1);
                }else {
                    map.put(a, 1);
                }
            }
            Collection<Integer> arr = map.values();
            int index = Collections.min(arr);
            for (Map.Entry c : map.entrySet()){
                if ((int)c.getValue() == index){
                    str = str.replace(String.valueOf(c.getKey()), "");
                }
            }
            System.out.println(str);
        }
    }
}
