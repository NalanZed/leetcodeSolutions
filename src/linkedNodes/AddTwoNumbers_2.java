package linkedNodes;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class AddTwoNumbers_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        //直接双指针
        ListNode p1 = l1,p2 = l2,resTmp = res;
        // 进位
        int carry = 0;
        while(p1!=null || p2!=null || carry!=0){
            int plus1 = p1!=null ? 0 : p1.val;
            int plus2 = p2!=null ? 0 : p1.val;
            int sumTmp = plus1 + plus2 + carry;
            carry = sumTmp / 10;
            sumTmp = sumTmp % 10;
            ListNode node = new ListNode(sumTmp);
            resTmp.next  = node;
            resTmp = resTmp.next;
        }
        return res.next;
    }
}
