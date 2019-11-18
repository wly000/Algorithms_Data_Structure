import java.util.Stack;

/**
 * @author leahy(583310958 @ qq.com)
 * @date 2019/11/18 16:29
 */
public class FindFirstCommonNode {
    public ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        ListNode p1 = pHead1, p2 = pHead2;
        while (p1 != null) {
            stack1.push(p1);
            p1 = p1.next;
        }
        while (p2 != null) {
            stack2.push(p2);
            p2 = p2.next;
        }
        ListNode temp = null;
        while (!stack1.empty() && !stack2.empty() && stack1.peek() == stack2.peek()) {
            stack1.pop();
            temp = stack2.pop();
        }
        return temp;
    }
}
