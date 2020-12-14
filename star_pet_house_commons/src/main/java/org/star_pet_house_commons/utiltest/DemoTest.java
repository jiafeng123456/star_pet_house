package org.star_pet_house_commons.utiltest;

import java.util.TreeSet;
import java.util.*;
/*
 *@description:
 *@author jiafeng
 *@date 2020/12/8 0008 11:05
 */
public class DemoTest {

    private static double jingdu = 0.001;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.printf("%.1f",niuDunIterator(sc.nextDouble()));
    }

    public static double niuDunIterator(double a){
        double n = 1,n_1 = n - ((n*n*n*n - a)/(4*n*n*n)) ;
        while(n-n_1 > jingdu || n -n_1 < -jingdu){
            n = n_1;
            n_1 = n - ((n*n*n*n - a)/(4*n*n*n)) ;
        }
        return n_1;
    }

    public static int getLastWordLength(String str){
        int n = str.substring(str.lastIndexOf(" ")+1, str.length()).length();
        return n;
    }
}
