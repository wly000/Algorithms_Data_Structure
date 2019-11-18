import java.util.ArrayList;
/**
 * @author leahy(583310958 @ qq.com)
 * @date 2019/11/17 17:39
 */
public class UglyNum {
    public static void main(String[] args) {
        int a = GetUglyNumber_Solution(8);
        System.out.println(a);
    }
    public static int GetUglyNumber_Solution(int index) {
        if(index<=0) return 0;
        int[] UglyNumbers = new int[index];
        UglyNumbers[0] = 1;
        int UglyIndex = 1;
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;
        while(UglyIndex < index) {
            UglyNumbers[UglyIndex] = min(2*UglyNumbers[index2], 3*UglyNumbers[index3], 5*UglyNumbers[index5]);
            //如果丑数数组最大的值是index2中得到的，那么更新index3和index5到最接近最大丑数的索引值。
            while(2*UglyNumbers[index2] <= UglyNumbers[UglyIndex]) index2++;
            while(3*UglyNumbers[index3] <= UglyNumbers[UglyIndex]) index3++;
            while(5*UglyNumbers[index5] <= UglyNumbers[UglyIndex]) index5++;

            UglyIndex++;
        }
        return UglyNumbers[index-1];
    }
    public static int min(int x1, int x2, int x3) {
        int minNum = x1;
        if(x1 > x2)
            minNum = x2;
        if(minNum > x3)
            minNum = x3;
        return minNum;
    }
}
