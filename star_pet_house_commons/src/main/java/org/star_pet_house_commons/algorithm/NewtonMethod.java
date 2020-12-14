package org.star_pet_house_commons.algorithm;

import java.util.Scanner;

/*
 *@description: 牛顿迭代法求根
 *@author jiafeng
 *@date 2020/12/10 0010 13:58
 */
public class NewtonMethod {

    //精度
    private static double jingdu = 0.0001;
    //几次方
    private static int i = 3;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            if(n<=1){
                System.out.println("0");
            }
            int sum = 0;
            while(n>1){
                int[] numArr = bottlesNum(n);
                n = numArr[1];
                sum += numArr[0];
            }
            System.out.println(sum);
        }
    }

    //递归
    public static int[] bottlesNum(int num){
        int[] numArr = new int[2];
        if(num == 2){
            numArr[0] = 1;
            numArr[1] = 0;
        }else{
            numArr[0] = num/3;
            numArr[1] = num/3 + num%3;
        }
        return numArr;
    }
    public static String getStr(String file_name){
        String[] strArr = file_name.split("\\\\");
        String str = strArr[strArr.length-1];
        String[] arr = str.split(" ");
        if(arr[0].length() > 16){
            str = arr[0].substring(arr[0].length()-16) + " " + arr[1];
        }
        return str;
    }

    public static double newtonMethod(double a){
        //几次方  m中相乘的n 就有多少个
        double n = 1 , m = n -((n*n*n - a)/(i*n*n));
        while (n-m > jingdu || n-m < -jingdu){
            n = m;
            m = n -((n*n*n - a)/(i*n*n));
        }
        return m;
    }
}
