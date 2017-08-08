/**
 * Author: 王俊超
 * Date: 2015-04-23
 * Time: 21:22
 * Declaration: All Rights Reserved !!!
 */
public class Test20 {
    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印每一个数字
     *
     * @param numbers 输入的二维数组，二维数组必须是N*M的，否则分出错
     */
    public static void printMatrixClockWisely(int[][] numbers) {
        // 输入的参数不能为空
        if (numbers == null) {
            return;
        }

        // 记录一圈（环）的开始位置的行(也是开始位置的列)
        int start = 0;
        // 对每一圈（环）进行处理，
        // 行号start<=行号最大值(numbers.length-1)/2 && 列号start<=列号最大值(numbers[0].length-1)/2
        while (start * 2 < numbers.length && start * 2 < numbers[0].length) {
            printMatrixInCircle(numbers, start);
            // 指向下一个要处理的的环的第一个位置
            start++;
        }
    }

    public static void printMatrixInCircle(int[][] numbers, int start) {

        int endX = numbers[0].length - 1 - start;
        int endY = numbers.length - 1 - start;

        // 输出环的上面一行，包括最中的那个数字
        for (int i = start; i <= endX; i++) {
            System.out.print(numbers[start][i] + " ");
        }

        // 环的高度至少为2才会输出右边的一列
        if (endY > start) {
            // 因为右边那一列的最上面那一个已经被输出了，所以行呈从start+1开始，
            // 输出包括右边那列的最下面那个
            for (int i = start + 1; i <= endY; i++) {
                System.out.print(numbers[i][endX] + " ");
            }
        }

        // 环的高度至少是2并且环的宽度至少是2才会输出下面那一行
        // cols-1-start：表示的是环最右那一列的列号
        if (endY > start && endX > start) {
            // 因为环的左下角的位置已经输出了，所以列号从cols-start-2开始
            for (int i = endX - 1; i >= start; i--) {
                System.out.print(numbers[endY][i] + " ");
            }
        }

        // 环的宽度至少是2并且环的高度至少是3才会输出最左边那一列
        if (endX > start && endY > start + 1) {
            // 因为最左边那一列的第一个和最后一个已经被输出了
            for (int i = endY - 1; i >= start + 1; i--) {
                System.out.print(numbers[i][start] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] numbers = {
                {1, 2, 3, 4, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8},
                {13, 12, 11, 10, 9},
        };
        printMatrixClockWisely(numbers);
        System.out.println();

        int[][] numbers2 = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {22, 23, 24, 25, 26, 27, 28, 9},
                {21, 36, 37, 38, 39, 40, 29, 10},
                {20, 35, 34, 33, 32, 31, 30, 11},
                {19, 18, 17, 16, 15, 14, 13, 12},

        };
        printMatrixClockWisely(numbers2);
        System.out.println();


        int[][] numbers3 = {
                {1, 2, 3, 4, 5, 6, 7, 8}
        };
        printMatrixClockWisely(numbers3);
        System.out.println();

        int[][] numbers4 = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {16, 15, 14, 13, 12, 11, 10, 9}
        };
        printMatrixClockWisely(numbers4);
        System.out.println();


        int[][] numbers5 = {
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
                {8}
        };
        printMatrixClockWisely(numbers5);
        System.out.println();

        int[][] numbers6 = {
                {0, 1},
                {15, 2},
                {14, 3},
                {13, 4},
                {12, 5},
                {11, 6},
                {10, 7},
                {9, 8}
        };
        printMatrixClockWisely(numbers6);
        System.out.println();


        int[][] numbers7 = {
                {1, 2},
                {4, 3}
        };
        printMatrixClockWisely(numbers7);
        System.out.println();

        int[][] numbers8 = {
                {1}
        };
        printMatrixClockWisely(numbers8);
        System.out.println();

        // 0个元素的数组
        printMatrixClockWisely(new int[][]{{}});
        // 空数组
        printMatrixClockWisely(null);
    }
}
