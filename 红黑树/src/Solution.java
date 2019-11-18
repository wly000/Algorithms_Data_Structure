/**
 * @author leahy(583310958 @ qq.com)
 * @date 2019/11/11 20:37
 */
public class Solution {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence == null || sequence.length <= 0) {
            return false;
        }
        int left = 0;
        int right = sequence.length - 1;
        return VerifySequenceOfBST_Core(sequence, left, right);
    }
    public boolean VerifySequenceOfBST_Core(int[] sequence, int left, int right) {
        if (right > left) {
            int root = sequence[right];
            int i = left;
            for (; i < right; i++) {
                if (sequence[i] > root) {
                    break;
                }
            }
            int j = i;
            for (; j < right; j++) {
                if (sequence[j] < root) {
                    return false;
                }
            }
            return VerifySequenceOfBST_Core(sequence, left, i-1)
                    && VerifySequenceOfBST_Core(sequence, i, right-1);
        }
        else {
            return true;
        }
    }
}