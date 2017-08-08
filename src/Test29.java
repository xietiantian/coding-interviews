import org.jetbrains.annotations.Contract;

/**
 * Author: 王俊超
 * Date: 2015-05-06
 * Time: 08:44
 * Declaration: All Rights Reserved !!!
 */
public class Test29 {

    /**
     * 题目：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字
     * Moore voting算法实现，O(n)
     *
     * @param numbers 输入数组
     * @return 找到的数字
     */
    @Contract("null -> fail")
    private static int moreThanHalfNum2(int[] numbers) {

        // 输入校验
        if (numbers == null || numbers.length < 1) {
            throw new IllegalArgumentException("array length must large than 0");
        }

        // Moore voting
        int count = 0, ret = 0;
        for (int num : numbers) {
            if (count == 0)
                ret = num;
            if (num != ret)
                count--;
            else
                count++;
        }

        // 最后的result可能不是出现次数大于数组一半长度的值
        // 统计result的出现次数，进行验证
        count = 0;
        for (int num : numbers) {
            if (ret == num) {
                count++;
            }
        }
        if (count > numbers.length / 2) {
            return ret;
        } else {// 寻找的元素不存在
            throw new IllegalArgumentException("majority element does not exist");
        }
    }

    /**
     * 题目：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字
     * 用快速排序中的partition方法实现，O(n)
     *
     * @param numbers
     * @return
     */
    @Contract("null -> fail")
    private static int moreThanHalfNum(int[] numbers) {

        // 输入校验
        if (numbers == null || numbers.length < 1) {
            throw new IllegalArgumentException("array length must large than 0");
        }

        // partition
        int mid = numbers.length >> 1;
        int start = 0, end = numbers.length - 1;
        int index = Sort.quickSortPartition(numbers, start, end);
        while (index != mid) {
            if (index > mid)
                end = index - 1;
            else
                start = index + 1;
            index = Sort.quickSortPartition(numbers, start, end);
        }

        // 最后的result可能不是出现次数大于数组一半长度的值
        // 统计result的出现次数，进行验证
        int count = 0;
        int ret = numbers[index];
        for (int num : numbers) {
            if (ret == num) {
                count++;
            }
        }
        if (count > numbers.length / 2) {
            return ret;
        } else {// 寻找的元素不存在
            throw new IllegalArgumentException("majority element does not exist");
        }
    }


    public static void main(String[] args) {
        // 存在出现次数超过数组长度一半的数字
        int numbers[] = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(moreThanHalfNum(numbers));

        // 出现次数超过数组长度一半的数字都出现在数组的前半部分
        int numbers2[] = {2, 2, 2, 2, 2, 1, 3, 4, 5};
        System.out.println(moreThanHalfNum(numbers2));

        // 出现次数超过数组长度一半的数字都出现在数组的后半部分
        int numbers3[] = {1, 3, 4, 5, 2, 2, 2, 2, 2};
        System.out.println(moreThanHalfNum(numbers3));

        // 只有一个数
        int numbers4[] = {1};
        System.out.println(moreThanHalfNum(numbers4));

        // 不存在出现次数超过数组长度一半的数字
        int numbers5[] = {1, 2, 3, 2, 4, 2, 5, 2, 3};
        moreThanHalfNum(numbers5);

        // 输入空指针
        moreThanHalfNum(null);
    }
}


