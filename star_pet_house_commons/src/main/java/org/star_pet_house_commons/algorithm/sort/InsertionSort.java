package org.star_pet_house_commons.algorithm.sort;

import java.util.Arrays;

/*
 *@description:排序算法比较
 * |   算法名称  | 时间复杂度(平均)/(最坏)/(最好)| 空间复杂度 | 稳定性
 *     插入排序         O(n^2)/O(n^2)/O(n)           O(1)       稳定
 *@author jiafeng
 *@date 2020/12/4 0004 09:48
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr1 = {4,7,5,1,2,3};
        System.out.println(Arrays.toString(InsertionSort(arr1)));
        System.out.println(Arrays.toString(InsertionSort2(arr1)));
    }

    public static int[] InsertionSort(int[] arr){
        int n = arr.length;
        for (int i = 0 ; i < n ; i++){
            int arrIndex = arr[i];
            int j = i-1;
            for (; j >= 0 && j < i ; j-- ){
                if (arrIndex < arr[j]){
                    arr[j + 1] = arr[j];
                }else {
                    break;
                }
            }
            arr[j + 1] = arrIndex;
        }
        return arr;
    }

    public static int[] InsertionSort2(int[] arr){
        int n = arr.length;
        for (int i = 0 ; i < n ; i++){
            int arrIndex = arr[i];
            int preIndex = i-1;
            while (preIndex >= 0 && arr[preIndex] > arrIndex){
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex+1] = arrIndex;
        }
        return arr;
    }
}
