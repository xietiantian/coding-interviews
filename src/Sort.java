import java.util.Arrays;

/**
 * Created by tiantian on 3/19/17.
 */
public class Sort {
    public static void bubbleSort(int[] data) {
        for (int i = data.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }

            }
        }
    }


    public static void quickSort(int[] data, int start, int end) {
        if (start < end) {
            int index = quickSortPartition(data, start, end);
            quickSort(data, start, index - 1);
            quickSort(data, index + 1, end);
        }
    }

    public static int quickSortPartition(int data[], int start, int end) {
        //选取第一个数做轴值,也可以随机选一个
        int pivot = data[start];//记录轴值

        int i = start;
        int j = end;

        while (i < j) {
            while (i < j && data[j] >= pivot) {//右侧扫描,i所指位置应该是轴值
                j--;
            }
            data[i] = data[j];
            while (i < j && data[i] <= pivot) {//左侧扫描，j所指位置应该是轴值
                i++;
            }
            data[j] = data[i];
        }
        data[i] = pivot;
        return i;
    }

    /**
     * 选择排序(不稳定)，时间复杂度O(n^2)，空间O(1)
     * <p>
     * 举个例子，序列5 8 5 2 9， 我们知道第一遍选择第1个元素5会
     * 和2交换，那么原序列中2个5的相对前后顺序就被破坏了，所以选择排
     * 序不是一个稳定的排序算法
     */
    public static void selectSort(int[] data) {
        //position用来记录剩下元素最小值的下标

        for (int i = 0; i < data.length - 1; i++) {//i表示无序区的开始
            //min用来记录最小值，minIndex用来记录最小值下标
            int minIndex = i;
            int min = data[i];
            for (int j = i + 1; j < data.length; j++) {//循环找到无序区的最小值
                if (data[j] < min) {
                    minIndex = j;
                    min = data[j];
                }
            }
            if (minIndex != i) {//如果无序区最小值不是无序区的第一个元素
                //将无序区最小值和无序区第一个元素交换
                data[minIndex] = data[i];
                data[i] = min;
            }
        }
    }


    /**
     * 用小根堆实现从大到小排序
     *
     * @param data 待排序数组
     */
    public static void heapSort(int[] data) {
        for (int i = data.length / 2 - 1; i >= 0; i--) {
            siftDown(data, i, data.length);
        }
        for (int i = 0; i < data.length - 1; i++) {
            int tmp = data[0];
            data[0] = data[data.length - 1 - i];
            data[data.length - i - 1] = tmp;
            siftDown(data, 0, data.length - i - 1);
        }
    }

    /**
     * 小根堆的筛选
     *
     * @param data 小根堆数组
     * @param i    待筛选节点下标
     * @param size 堆的节点个数，data[0]-data[size-1]是堆的节点
     */
    public static void siftDown(int data[], int i, int size) {
        int j = (i << 1) + 1;//j是i的左孩子
        int tmp = data[i];//记录节点i的数值
        while (j < size) {
            if (j + 1 < size && data[j] > data[j + 1]) j++;//j是左右孩子中的较小者
            if (tmp < data[j]) break;//符合小根堆条件不需要调整
            //不符合小根堆条件“交换”节点i和节点j值
            data[i] = data[j];
            //更新i，下次循环中再对i进行筛选
            i = j;
            j = (i << 1) + 1;
        }
        data[i] = tmp;
    }

    public static void insertSort(int[] data) {
        for (int i = 1; i < data.length; i++) {//i是无序区的起始位置，第i个元素是待插入元素，i前面是有序区
            int toInsert = data[i];
            int j = i - 1;
            for (; j >= 0 && data[j] > toInsert; j--) {
                data[j + 1] = data[j];
            }
            data[j + 1] = toInsert;
        }
    }

    public static void ShellInsertSort(int data[]) {
        for (int d = data.length >> 1; d >= 1; d >>= 1) {//d是每趟排序的增量
            for (int i = d; i < data.length; i++) {//i表示待插入的元素
                if (data[i] < data[i - d]) {
                    int j = i - d, toInsert = data[i];
                    //以d为间隔找到i前面以d为增量的有序区内的插入位置
                    for (; j >= 0 && toInsert < data[j]; j -= d) {
                        data[j + d] = data[j];
                    }
                    data[j + d] = toInsert;
                }
            }
        }
    }


    public static void mergeSort(int[] data) {
        mergeSort(data, 0, data.length - 1);
    }

    public static void mergeSort(int[] data, int left, int right) {
        if (left >= right)
            return;
        // 找出中间索引
        int center = (left + right) / 2;
        // 对左边数组进行递归
        mergeSort(data, left, center);
        // 对右边数组进行递归
        mergeSort(data, center + 1, right);
        // 合并
        merge(data, left, center, right);
    }

    /**
     * 将两个数组进行归并，归并前面2个数组已有序，归并后依然有序
     *
     * @param data   数组对象
     * @param left   左数组的第一个元素的索引
     * @param center 左数组的最后一个元素的索引，center+1是右数组第一个元素的索引
     * @param right  右数组最后一个元素的索引
     */
    public static void merge(int[] data, int left, int center, int right) {

        int[] tmpArr = new int[data.length];// 临时数组
        int p1 = left;// 左数组第一个元素的索引
        int p2 = center + 1;// 右数组第一个元素索引
        int i = left;// i 记录临时数组的索引
        while (p1 <= center && p2 <= right) {
            // 从两个数组中取出最小的放入临时数组
            if (data[p1] <= data[p2]) {
                tmpArr[i++] = data[p1++];
            } else {
                tmpArr[i++] = data[p2++];
            }
        }
        // 剩余部分依次放入临时数组（实际上两个while只会执行其中一个）
        while (p2 <= right) {
            tmpArr[i++] = data[p2++];
        }
        while (p1 <= center) {
            tmpArr[i++] = data[p1++];
        }
        // 将临时数组中的内容拷贝回原数组中
        // （原left-right范围的内容被复制回原数组）
        for (p1 = left; p1 <= right; p1++) {
            data[p1] = tmpArr[p1];
        }
    }


    public static void radixSort(int[] data, int radix, int d) {
        // 缓存数组
        int[] tmp = new int[data.length];
        // buckets用于记录待排序元素的信息
        // buckets数组定义了max-min个桶
        int[] buckets = new int[radix];

        for (int i = 0, rate = 1; i < d; i++) {

            // 重置count数组，开始统计下一个关键字
            Arrays.fill(buckets, 0);
            // 将data中的元素完全复制到tmp数组中
            System.arraycopy(data, 0, tmp, 0, data.length);

            // 计算每个待排序数据的子关键字
            for (int j = 0; j < data.length; j++) {
                int subKey = (tmp[j] / rate) % radix;
                buckets[subKey]++;
            }

            for (int j = 1; j < radix; j++) {
                buckets[j] = buckets[j] + buckets[j - 1];
            }

            // 按子关键字对指定的数据进行排序
            for (int m = data.length - 1; m >= 0; m--) {
                int subKey = (tmp[m] / rate) % radix;
                data[--buckets[subKey]] = tmp[m];
            }
            rate *= radix;
        }

    }

    public static void printData(int[] data) {
        for (int i : data) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{49, 38, 65, 97, 49, 76};
        bubbleSort(nums);
        printData(nums);

        nums = new int[]{49, 38, 65, 97, 49, 76};
        quickSort(nums, 0, nums.length - 1);
        printData(nums);

        nums = new int[]{49, 38, 65, 97, 49, 76};
        selectSort(nums);
        printData(nums);

        nums = new int[]{49, 38, 65, 97, 49, 76};
        insertSort(nums);
        printData(nums);

        nums = new int[]{49, 38, 65, 97, 49, 76};
        ShellInsertSort(nums);
        printData(nums);

        nums = new int[]{49, 38, 65, 97, 49, 76};
        heapSort(nums);
        printData(nums);

        nums = new int[]{49, 38, 65, 97, 49, 76};
        mergeSort(nums);
        printData(nums);
    }
}
