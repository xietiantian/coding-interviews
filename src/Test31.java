/**
 * Author: 王俊超
 * Date: 2015-06-10
 * Time: 19:54
 * Declaration: All Rights Reserved !!!
 */
public class Test31 {
    /**
     * 题目2 输入一个整型数组，数组里有正数也有负数。数组中一个或连
     * 续的多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为O(n)。
     *
     * @param array 输入数组
     * @return 最大的连续子数组和
     */
    public static int findGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length < 1) return 0;

        int sum = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            sum += array[i];
            if (sum < array[i]) {
                sum = array[i];
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] data = {1, -2, 3, 10, -4, 7, 2, -5};
        int[] data2 = {-2, -8, -1, -5, -9};
        int[] data3 = {2, 8, 1, 5, 9};

        System.out.println(findGreatestSumOfSubArray(data));
        System.out.println(findGreatestSumOfSubArray(data2));
        System.out.println(findGreatestSumOfSubArray(data3));
    }
}
