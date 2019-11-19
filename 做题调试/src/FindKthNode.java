/**
 * @author leahy(583310958 @ qq.com)
 * @date 2019/11/19 14:29
 */
public class FindKthNode {
    public static void main(String[] args) {
        TreeNode pHead = new TreeNode(8);
        pHead.left = new TreeNode(6);
        pHead.right = new TreeNode(10);
        TreeNode a = pHead.left, b = pHead.right;
        a.left = new TreeNode(5);
        a.right = new TreeNode(7);
        b.left = new TreeNode(9);
        b.right = new TreeNode(11);
        TreeNode t = KthNode(pHead, 2);
        System.out.println(t.val);
    }
    public static TreeNode KthNode(TreeNode pRoot, int k)
    {
        if(pRoot == null || k <= 0) {
            return null;
        }
        TreeNode target = KthNodeCore(pRoot, k);
        return target;
    }
    static int count = 0;
    public static TreeNode KthNodeCore(TreeNode pRoot, int k) {
        TreeNode target = null;
        if (pRoot.left != null) {
            target = KthNodeCore(pRoot.left, k);
        }
        if (target == null) {
            count++;
            if (k == count) {
                target = pRoot;
            }
        }

        if (target == null && pRoot.right != null) {
            target = KthNodeCore(pRoot.right, k);
        }
        return target;
    }


}
