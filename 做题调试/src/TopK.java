import java.util.PriorityQueue;

/**
 * @author leahy(583310958 @ qq.com)
 * @date 2019/11/15 21:08
 */
public class TopK {
    public static void main(String[] args) {
        int[] datas = {2,4,5,0,1,11,45,6,10,57,30};
        int[] topK = findKthLargest03(datas, 5);
        for(int k : topK) {
            System.out.println(k);
        }
    }

    /**
     * 使用小堆顶
     * JDK中自带的PriorityQueue
     */
    public static int[] findKthLargest01(int[] nums, int k) {
        int[] result = new int[k];
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        for (int num : nums) {
            if (minQueue.size() < k || num > minQueue.peek()) {
                minQueue.offer(num);
            }
            if (minQueue.size() > k) {
                minQueue.poll();
            }
        }
        int  i = 0;
        while (minQueue.size() != 0) {
            result[i] = minQueue.poll();
            i++;
        }
        return result;
    }

    /**
     * 使用小堆顶
     * 自己造轮子
     */
    public static int[] findKthLargest02(int[] nums, int k) {
        //初始化一个含有k个元素的数组
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = nums[i];
        }
        //构造最小堆
        for(int i = result.length/2 -1; i >= 0; i--) {
            buildHeap(result,i,result.length);
        }
        //更新迭代，得到TopK
        for (int j = k; j < nums.length; j++) {
            int temp = result[0];
            if (nums[j] > temp) {
                result[0] = nums[j];
                buildHeap(result, 0, result.length);
            }
        }
        return result;
    }
    public static void buildHeap(int[] nums, int index, int length) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;
        if (left < length && nums[left] < nums[largest]) {
            largest = left;
        }
        if (right < length && nums[right] < nums[largest]) {
            largest = right;
        }
        if (index != largest){
            Swap(nums, largest, index);
            buildHeap(nums, largest, length);
        }
    }
    public static void Swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 采用快排的方法
     * 不稳定
     */
    public static int[] findKthLargest03(int[] nums, int k) {
        int n = quickSelect(nums, k, 0,nums.length - 1);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = nums[i];
        }
        return result;
    }
    public static int quickSelect(int[] nums, int k, int left, int right) {
        if (left == right)
            return right;
        int index = partition(nums, left, right);
        if(index - left + 1 > k) {
            return quickSelect(nums, k, left, index - 1);
        }
        else if (index - left + 1== k) {
            return index;
        }
        else
            return quickSelect(nums, k -index + left - 1, index + 1, right);
    }
    public static int partition(int[] nums, int left, int right) {
        int partitionIndex = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > nums[left]) {
                partitionIndex++;
                if (partitionIndex != i) {
                    Swap(nums, partitionIndex, i);
                }
            }
        }
        Swap(nums, partitionIndex, left);
        return partitionIndex;
    }
}
