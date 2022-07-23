package linkedNodes;

import java.util.*;

public class RemoveNthFromEnd_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode res = head;
        //存到map方便随机访问
        Map<Integer,ListNode> map = new HashMap<>();
        ListNode cur = head;
        int index = 0;
        while(cur!=null){
            map.put(index,cur);
            cur = cur.next;
            ++index;
        }
        // 定位到要删除节点的前一个节点
        int theNode = index - n - 1;
        //要删除的是第一个
        if(theNode == -1){
            res = head.next;
            head.next = null;
        }else {
            //获取到前面一个
            ListNode deleter = map.get(theNode);
            ListNode node = deleter.next;
            deleter.next = node.next;
            node.next = null;
        }
        return res;
    }
    //优质题解，用先后指针，first与second保持n的距离，当first到顶时，second正好在倒数第n+1个
    public ListNode removeNthFromEnd_standard(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd_cyc(ListNode head, int n) {
        ListNode temp = head;
        ArrayList<ListNode> list = new ArrayList<>();
        //走到最后一个节点
        while (temp.next != null){
            list.add(temp);
            temp = temp.next;
        }
        //获取删除元素前一个和后一个
        ListNode l = list.get(list.size() - n);
        if (n - 2 > 0){
            ListNode r = list.get(list.size() - n + 2);
            l.next = r;
        }else l.next = null;


        return head;
    }

    public static void main(String[] args) {
        RemoveNthFromEnd_19 p = new RemoveNthFromEnd_19();

        ListNode node3 = new ListNode(2,null);
        ListNode node2 = new ListNode(1,node3);
        ListNode node = new ListNode(0,node2);
        p.removeNthFromEnd_cyc(node,3);
    }
}
