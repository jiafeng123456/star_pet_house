package org.star_pet_house_commons.algorithm.sort;

import java.util.Arrays;

/*
 *@description:排序算法比较
 * |   算法名称  | 时间复杂度(平均)/(最坏)/(最好)| 空间复杂度 | 稳定性
 *     选择排序         O(n^2)/O(n^2)/O(n^2)         O(1)      不稳定
 *@author jiafeng
 *@date 2020/12/3 0003 16:36
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr1 = {4,7,5,1,2,3};
        System.out.println(Arrays.toString(selectionSort(arr1)));
    }

    public static int[] selectionSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            for (int j = i + 1 ; j < arr.length; j++){
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            int tem = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tem;
        }
        return arr;
    }
}
