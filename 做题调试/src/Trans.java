/**
 * @author leahy(583310958 @ qq.com)
 * @date 2019/11/17 15:07
 */
public class Trans {
    public static void main(String[] args) {
        int[] input = {1,2,2,5,8};
        int a = translationCount(input, input.length);
        System.out.println(a);
    }


    public static int translationCount(int[] nums, int length) {
        int[] count = new int[length];
        for (int i = length-1; i >= 0; i--) {
            if (i < length) {
                count[i] = 1;
                if (i < length - 2) {
                    count[i] = count[i+1] + combine(nums[i],nums[i+1])*count[i+2];
                }
                else if (i < length - 1) {
                    count[i] = count[i+1];
                }
            }
        }
        return count[0];
    }
    public static int combine(int i, int j) {
        int a = i*10 + j;
        if (10 <= a && 25 >= a) {
            return 1;
        }
        else
            return 0;
    }
}
