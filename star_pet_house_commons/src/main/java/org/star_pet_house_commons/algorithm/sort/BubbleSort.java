package org.star_pet_house_commons.algorithm.sort;

import java.util.Arrays;

/*
 *@description:排序算法比较
 * |   算法名称  | 时间复杂度(平均)/(最坏)/(最好)| 空间复杂度 | 稳定性
 *     冒泡排序         O(n^2)/O(n^2)/O(n)           O(1)       稳定
 *@author jiafeng
 *@date 2020/12/3 0003 16:09
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr1 = {4,7,5,1,2,3};
        System.out.println(Arrays.toString(bubbleSort(arr1)));
    }

    public static int[] bubbleSort(int[] arr1){
        int n = arr1.length;
        for (int i = 0 ; i < n - 1 ; i++){
            for (int j = 0; j <  n - 1 - i ; j++){
                if (arr1[j] > arr1[j+1]){
                    int tem = arr1[j];
                    arr1[j] = arr1[j+1];
                    arr1[j+1] = tem;
                }
            }
        }
        return arr1;
    }
}
