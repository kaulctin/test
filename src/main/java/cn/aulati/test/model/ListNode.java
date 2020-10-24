package cn.aulati.test.model;

/**
 * singly-linked list node.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    /**
     * Create a list from given int array.
     */
    public static ListNode fromArray(int[] nums) {
        ListNode root, cur;
        root = cur = new ListNode(0);

        for (int i = 0; i < nums.length; i++) {
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }

        return root.next;
    }

    /**
     * 把这个单向链表以"->"链接起来，返回一个字符串
     * @param r 链表的根节点
     * @return 拼接成的字符串
     */
    public static String printString(ListNode r) {
        if (r == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        ListNode cur = r;
        sb.append(cur.val);
        cur = cur.next;
        while (cur != null) {
            sb.append("->");
            sb.append(cur.val);
            cur = cur.next;
        }

        return sb.toString();
    }

}