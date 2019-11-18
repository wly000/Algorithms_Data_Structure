/**
 * @author leahy(583310958 @ qq.com)
 * @date 2019/11/7 19:41
 */
public class TraTree {
    public static void main(String[] args) {
        ListNode a = null;
        a = buildListNode();
        System.out.println(pre(a));
        System.out.println(mid(a));
        System.out.println(end(a));
    }

    public static String pre(ListNode head) {
        StringBuffer result = new StringBuffer();
        result.append(head.val);
        if (head.left != null) {
            result.append(pre(head.left));
        }
        if (head.right != null) {
            result.append(pre(head.right));
        }
        return result.toString();
    }

    public static String mid(ListNode head) {
        StringBuffer result = new StringBuffer();
        if (head.left != null) {
            result.append(mid(head.left));
        }
        result.append(head.val);
        if (head.right != null) {
            result.append(mid(head.right));
        }
        return result.toString();
    }

    public static String end(ListNode head) {
        StringBuffer result = new StringBuffer();
        if (head.left != null) {
            result.append(end(head.left));
        }
        if (head.right != null) {
            result.append(end(head.right));
        }
        result.append(head.val);
        return result.toString();
    }

    public static ListNode buildListNode() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        ListNode f = new ListNode(6);
        ListNode g = new ListNode(7);
        ListNode h = new ListNode(8);
        ListNode i = new ListNode(9);
        a.left = b;
        a.right = c;
        b.left = d;
        d.left = g;
        d.right = h;
        c.left = e;
        c.right = f;
        e.right = i;
        return a;
    }
}
