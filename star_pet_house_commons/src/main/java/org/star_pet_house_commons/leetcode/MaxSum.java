package org.star_pet_house_commons.leetcode;

import java.util.HashMap;

/*
 *@description:
 * 你有两个 有序 且数组内元素互不相同的数组 nums1 和 nums2 。
 * 一条 合法路径 定义如下：
 * 选择数组 nums1 或者 nums2 开始遍历（从下标 0 处开始）。
 * 从左到右遍历当前数组。
 * 如果你遇到了 nums1 和 nums2 中都存在的值，那么你可以切换路径到另一个数组对应数字处继续遍历（但在合法路径中重复数字只会被统计一次）。
 * 得分定义为合法路径中不同数字的和。
 * 请你返回所有可能合法路径中的最大得分。
 * 由于答案可能很大，请你将它对 10^9 + 7 取余后返回。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/get-the-maximum-score
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@author jiafeng
 *@date 2020/12/3 0003 14:23
 */
public class MaxSum {

    public static void main(String[] args) {
        int[] a = {2,4,5,8,10};
        int[] b = {4,6,8,9};
        System.out.println(maxSum1(a, b));
    }

    public static int maxSum1(int[] a, int[] b) {
        int n = a.length, m = b.length;
        HashMap<Integer, Integer> mp = new HashMap<Integer, Integer>();
        for(int i = 0; i < m; i++){
            mp.put(b[i], i);  // 预处理b[i]数组的位置
        }
        long res = 0, sum1 = 0, sum2 = 0, mod = 1000000007;
        int j = 0;
        for(int i = 0; i < n; i++) {
            sum1 += a[i];  // 计算a数组这部分的和
            if(mp.get(a[i]) != null) {  // 说明a[i]在b数组中也存在
                int pos = mp.get(a[i]);
                for(; j <= pos; j++) {  // 开始计算b数组这部分的和
                    sum2 += b[j];
                }
                res = (res + Math.max(sum1, sum2)) % mod;  // 选择更大的那部分加
                sum1 = sum2 = 0;  // 归零，开始下一部分的计算
            }
        }
        for(; j < m; j++) {  // b数组可能还没走完
            sum2 += b[j];
        }
        res = (res + Math.max(sum1, sum2)) % mod;
        return (int)(res % 1000000007);
    }
}
