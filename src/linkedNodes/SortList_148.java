package linkedNodes;

public class SortList_148 {
    public ListNode sortList(ListNode head) {
        if(head==null){
            return head;
        }
        ListNode tmpNode = head;
        ListNode preNode = head;
        ListNode node = head.next;
        while (node!=null){
            if(node.val<tmpNode.val){
                preNode.next = node.next;
                node.next = tmpNode;
            }
            preNode = preNode.next;
            if(preNode!=null){
                node = preNode.next;
            }else {
                node = null;
            }
        }


        return head;
    }
    public ListNode sortList_guibing(ListNode head){
        // 如果分组内只剩0个或一个元素，直接返回head啦
        if(head==null || head.next == null){
            return head;
        }
        //归并的关键步骤，划分
        //先用快慢指针找到中间（偶数时为中左）node
        //一分为二，left指向原来的头，right指向断开后右边的头
        ListNode low = head,fast = head;
        while(fast.next!=null && fast.next.next!=null){
            low = low.next;
            fast = fast.next.next;
        }
        // 断开！
        ListNode tmp = low.next;
        low.next = null;
        //递归分区
        ListNode left = sortList_guibing(head);
        ListNode right = sortList_guibing(tmp);

        // 分完区之后，开始归并
        // 先建一个哨兵node，这样就可以直接进入选择环节了。在左右分区中找最小的
        // 哨兵node
        ListNode partHead = new ListNode(-1);
        ListNode headTmp = partHead;
        while(left!=null || right !=null){
            if(left == null){
                partHead.next = right;
                right = right.next;
            }else if(right==null){
                partHead.next = left;
                left = left.next;
            }else if(left.val<= right.val){
                partHead.next = left;
                left = left.next;
            }else {
                partHead.next = right;
                right = right.next;
            }
            partHead = partHead.next;
        }
        return headTmp.next;
    }
}
