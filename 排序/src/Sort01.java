import java.util.Arrays;
import java.util.Random;

/**
 * @author leahy(583310958 @ qq.com)
 * @date 2019/11/2 10:39
 */
public class Sort01 {

    public static void main(String[] args) {

        //待排序数组
        //double[] arr = {45,2.1,3,67,21,90,20,13,45,23,12,34,56,78,90,0,1,2,3,1,2,9,7,8,4,6};
//        int len = 10000;
//        double[] arr = new double[len];
//        for (int i = 0; i < len; i++) {
//            Random random = new Random(i);
//            arr[i] = random.nextDouble();
//        }
        double[] arr = {2,3,4,6,1,9,2,1,5,7,3};
        long startTime = System.nanoTime();
        //Sort(arr);
        long endTime = System.nanoTime();
        System.out.println("排序运行时间： " + (endTime - startTime) + "ns");
        System.out.println(Arrays.toString(arr));

        int[] a = new int[0];
        if (a == null || a.length <= 1) {
            System.out.println(Arrays.toString(a));
        }
    }

    /**
     * 数组元素互换函数
     */
    public static double[] swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }

    /**
     * 冒泡排序
     */
    public static double[] bubbleSort(double[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j+1] < arr[j]) {
                    swap(arr, j+1, j);
                }
            }
        }
        return arr;
    }

    /**
     * 插入排序
     */
    public static double[] insertSort(double[] arr) {
        int len = arr.length;
        double cur = 0;
        for (int i = 1; i < len; i++) {
            cur = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > cur) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = cur;
        }
        return arr;
    }

    /**
     * 选择排序
     */
    public static double[] selectSort(double[] arr) {
        int len = arr.length;
        for(int i = 0; i < len; i++) {
            int j = i + 1;
            //记录最小元素的位置
            int count = i;
            //记录最小元素的值
            double temp = arr[i];
            while (j < len) {
                if (arr[j] < temp) {
                    //改变最小元素的值
                    temp = arr[j];
                    //改变最小元素的位置
                    count = j;
                }
                j++;
            }
            swap(arr, i, count);
        }
        return arr;
    }

    /**
     * 希尔排序
     */
    public static double[] shellSort(double[] arr) {
        int len = arr.length;
        int h = 0;
        while (h < len/3) {
            h = h*3 + 1;
        }
        while (h > 0) {
            for(int i = h; i < len; i++) {
                int j = i - h;
                //保存待插入元素
                double cur = arr[i];
                while (j >= 0 && arr[j] > cur) {
                    arr[j+h] = arr[j];
                    j-=h;
                }
                arr[j+h] = cur;
            }
            h/=3;
        }
        return arr;
    }

    /**
     * 快速排序
     */
    public static double[] quickSort(double[] arr) {
        int left = 0;
        int right = arr.length - 1;
        quickDivide(arr, left, right);
        return arr;
    }
    public static void quickDivide(double[] arr, int left, int right) {
        int partitionIndex = 0;
        if (left < right) {
            partitionIndex = quickPartition(arr, left, right);
            quickDivide(arr, left, partitionIndex - 1);
            quickDivide(arr, partitionIndex + 1, right);
        }
    }
    public static int quickPartition(double[] arr, int left, int right) {
        //记录分区的索引
        int partitionIndex = left;
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] < arr[left]) {
                partitionIndex++;
                if (partitionIndex != i) {
                    swap(arr, partitionIndex, i);
                }
            }
        }
        swap(arr, partitionIndex, left);
        return partitionIndex;
    }

    /**
     * 归并排序
     */

}
