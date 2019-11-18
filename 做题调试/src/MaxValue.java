/**
 * @author leahy(583310958 @ qq.com)
 * @date 2019/11/17 15:56
 */
public class MaxValue {
    public static void main(String[] args) {
        int[][] mat = {{1,10,3,8},{12,2,9,6},{5,7,4,11},{3,7,16,5}};
        int a = findMax(mat, 4, 4);
        System.out.println(a);
    }
    public static int findMax(int[][] mat, int rows, int cols) {
        int[][] maxValue = new int[rows][cols];
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int goRight = 0;
                int goDown = 0;
                if (i > 0) {
                    goRight = maxValue[i-1][j];
                }
                if (j > 0){
                    goDown = maxValue[i][j-1];
                }
                maxValue[i][j] = max(goDown,goRight) + mat[i][j];
            }
        }
        return maxValue[rows-1][cols-1];
    }
    public static int max(int a, int b) {
        if ( a > b) {
            return a;
        }
        else
            return b;
    }
}
