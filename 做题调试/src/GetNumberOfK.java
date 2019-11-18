/**
 * @author leahy(583310958 @ qq.com)
 * @date 2019/11/18 16:58
 */
public class GetNumberOfK {
    public static void main(String[] args) {
        int[] array = {1,3,3,3,3,3,4,5};

        long startTime = System.nanoTime();
        int a = GetNumberOfK(array, 3);
        long endTime = System.nanoTime();
        System.out.println("排序运行时间： " + (endTime - startTime) + "ns");
        System.out.println(a);
    }
    public static int GetNumberOfK(int [] array , int k) {
        if (array == null || array.length <= 0) {
            return 0;
        }
        int left = getFirst(array, k);
        int right = getLast(array, k);
        if (left <= right) {
            return right - left + 1;
        }
        else {
            return 0;
        }
    }

    public static int getFirst(int array[], int k) {
        int index = array.length;
        index = index >> 1;
        while (index <= array.length - 1 && array[index] > k ) {
            index = (index + array.length - 1) / 2;
        }
        while (index >= 0 && array[index] < k) {
            index = index >> 1;
        }
        while (index >= 0 && array[index] == k) {
            index--;
        }
        return ++index;
    }
    public static int getLast(int array[], int k) {
        int index = array.length;
        index = index >> 1;
        while (index <= array.length - 1 && array[index] > k ) {
            index = (index + array.length - 1) / 2;
        }
        while (index >= 0 && array[index] < k) {
            index = index >> 1;
        }
        while (index <= array.length - 1 && array[index] == k) {
            index++;
        }
        return --index;
    }

}
