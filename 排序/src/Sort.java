import java.io.*;
import java.util.*;

/**
 * @author leahy(583310958 @ qq.com)
 * @date 2019/11/1 13:05
 */
public class Sort {

    public static void main(String[] args) throws IOException {

        //待排序数组
        //double[] arr = {45,2.1,3,67,21,90,20,13,45,23,12,34,56,78,90,0,1,2,3,1,2,9,7,8,4,6};
        int len = 10000;
        double[] arr = new double[len];
        for (int i = 0; i < len; i++) {
            Random random = new Random(i);
            arr[i] = random.nextDouble();
        }
        //double[] arr = {2,3,4,6,1,9,2,1,5,7,3};
        long startTime = System.nanoTime();
        heapSort(arr);
        long endTime = System.nanoTime();
        System.out.println("排序运行时间： " + (endTime - startTime) + "ns");
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 冒泡排序
     */
    public static double[] bubbleSort(double[] arr) {

        int len = arr.length;
        double temp = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
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
        //待插入的值
        double cur = 0;

        for (int i = 1; i < len; i++) {
            int j = i - 1;
            cur = arr[i];
            while(j >= 0 && arr[j] > cur) {
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

        for (int i = 0; i < len; i++) {
            double temp = arr[i];
            int j = i + 1;
            int count = 0;
            while (j < len) {
                if (arr[j] < temp) {
                    temp = arr[j];
                    count = j;
                }
                j++;
            }
            if (count != 0) {
                arr[count] = arr[i];
                arr[i] = temp;
            }
        }
        return arr;
    }

    /**
     * 希尔排序
     */
    public static double[] shellSort(double[] arr) {
        int len = arr.length;
        //子序列增量
        int h = 1;
        while (h < len/3) {
            h = h*3 + 1;
        }
        while (h >= 1) {

            //插入排序（这里在插入过程中进行了元素位置的互换，实际上也可以用上面的插入排序，其没有
            // 进行元素的互换，而是直接将值插入到合适的位置。）
            for (int i = h; i < len; i++) {
                int j = i;
                while (j >= h && arr[j - h] > arr[j]) {
                    double temp = arr[j];
                    arr[j] = arr[j - h];
                    arr[j - h] = temp;
                    j-=h;
                }
            }

            h/=3;
        }
        return arr;
    }

    /**
     * 归并排序
     */
    public static double[] mergeSort(double[] arr) {
        int start = 0;
        int end = arr.length - 1;
        mergeDivide(arr, start, end);
        return arr;
    }
    public static void mergeDivide(double[] arr, int start, int end) {
        if (start < end) {
            int middle = (start + end)/2;
            mergeDivide(arr, start, middle);
            mergeDivide(arr, middle + 1, end);
            mergeUnit(arr, start, middle, end);
        }
    }
    public static void mergeUnit(double[] arr, int left, int middle, int right) {
        double[] temp = new double[arr.length];
        //构建探测指针和存放指针,p1和p2是探测指针，s是存放指针
        int p1 = left, p2 = middle + 1, s = left;
        //因为现在子序列已经是有序的，所有将排好序的子序列进行合并，如下是合并的算法
        while(p1 <= middle && p2 <= right) {
            if (arr[p1] <= arr[p2]) {
                temp[s++] = arr[p1++];
            }
            else {
                temp[s++] = arr[p2++];
            }
        }
        while (p1 <= middle) {
            temp[s++] = arr[p1++];
        }
        while (p2 <= right) {
            temp[s++] = arr[p2++];
        }

        //复制回原数组
        for (int i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }

    /**
     * 快速排序之单向扫描
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
        //记录分区标志元素的索引
        int p = left;
        for (int i = left+1; i <= right; i++) {
            if (arr[i] < arr[left]) {
                p++;
                //整个算法，最巧妙的地方。将分区标志元素优雅地放到合适的地方。
                if (p != i) {
                    double temp = arr[p];
                    arr[p] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        double temp = arr[p];
        arr[p] = arr[left];
        arr[left] = temp;
        return p;
    }

    /**
     * 快速排序之双向扫描
     */
    public static double[] quickSort1(double[] arr) {
        int left = 0;
        int right = arr.length - 1;
        quickDivide1(arr, left, right);
        return arr;
    }
    public static void quickDivide1(double[] arr, int left, int right) {
        int partitionIndex = 0;
        if (left < right) {
            partitionIndex = quickPartition1(arr, left, right);
            quickDivide1(arr, left, partitionIndex - 1);
            quickDivide1(arr, partitionIndex + 1, right);
        }
    }
    public static int quickPartition1(double[] arr, int left, int right) {
        //记录分区标志元素的索引
        int i = left, j = right;

        while (i < j) {
            while (arr[i] < arr[right]) {
                i++;
            }
            while (i < j & arr[j] >= arr[right]) {
                j--;
            }
            if (i < j) {
                double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        double temp = arr[i];
        arr[i] = arr[right];
        arr[right] = temp;
        return i;
    }

    /**
     * 堆排序
     */
    public static double[] heapSort(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            bigHeap(arr,i);
        }
        return arr;
    }
    public static void bigHeap(double[] arr, int i) {
        //树的深度
        int n = (arr.length - i) / 2 - 1;
        //构造大堆顶
        double temp = 0;
        while (n >= 0) {
            if (arr[2 * n + 1] < arr[n]) {
                temp = arr[n];
                arr[n] = arr[2 * n + 1];
                arr[2 * n + 1] = temp;
            }
            if ((2 * n + 2) >= arr.length - i - 1) {
                if (arr[2 * n + 2] > arr[n]) {
                    temp = arr[n];
                    arr[n] = arr[2 * n + 2];
                    arr[2 * n + 2] = temp;
                }
            }
            n--;
        }
        temp = arr[0];
        arr[0] = arr[arr.length - i - 1];
        arr[arr.length - i - 1] = temp;
    }
    /**
    * 计数排序
    */
    public static int[] countSort(int[] arr) {
    int[] result = new int[10];
    for (int i = 0; i < arr.length; i++) {
        result[arr[i]]+=1;
    }
    int j = 0;
    for (int i = 0; i < result.length; i++) {
        int temp = result[i];
        while(temp > 0) {
            arr[j] = i;
            temp--;
            j++;
        }
    }
    return arr;
    }

/**
 * 基数排序
 */
public static int[] radixSort(int[] arr) {
    //计算出最大的数的位数
    int max = arr[0];
    for (int i = 1; i < arr.length; i++) {
        max = Math.max(max, arr[i]);
    }
    int maxDigit = 0;
    while (max != 0) {
        max /= 10;
        maxDigit++;
    }
    int mod = 10, div = 1;
    ArrayList<ArrayList<Integer>> bucketList = new ArrayList<ArrayList<Integer>>();
    for (int i = 0; i < 10; i++) {
        bucketList.add(new ArrayList<Integer>());
    }
    for (int i = 0; i < maxDigit; i++, mod*=10, div*=10) {
        for (int j = 0; j < arr.length; j++) {
            int num = (arr[j] % mod) / div;
            bucketList.get(num).add(arr[j]);
        }
        int index = 0;
        for (int j = 0; j < bucketList.size(); j++) {
            for (int k = 0; k < bucketList.get(j).size(); k++) {
                arr[index++] = bucketList.get(j).get(k);
            }
            bucketList.get(j).clear();
        }
    }
    return arr;
}
}
