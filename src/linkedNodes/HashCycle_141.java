package linkedNodes;

import org.w3c.dom.Node;

import java.util.*;

public class HashCycle_141 {
    public boolean hasCycle(ListNode head) {
        ListNode low = head;
        ListNode fast = head;
        while(fast!=null && fast.next != null){
            low = low.next;
            fast = fast.next.next;
            if(low == fast){
                return true;
            }
        }
        return false;
    }
    public int getIndex(ListNode root){
        int L1 = getLinkLen(root);
        int L2 = getCycleLen(root);
        // 非环长，即low进入环时经过的跳数
        int noSycleLen = L1 - L2;
        // detaL 是 low进入环时 与fast之间的距离
        int detaL = noSycleLen % L2;
        // fast步长为2， low步长为1
        // 有 2*x + detaL = 1*x + 环长L2
        // 简化： x = L2 - detaL
        int detaN = L2 - detaL;
        return  detaN;
    }
    public int getCycleLen(ListNode root){
        ListNode low = root;
        ListNode fast = root;
        int firstMeet=0;
        int secondMeet = 0;
        do{
            low = low.next;
            fast = fast.next.next;
            ++firstMeet;
        }while(low!=fast);
        secondMeet = firstMeet;
        do{
            low = low.next;
            fast = fast.next.next;
            ++secondMeet;
        }while(low!=fast);
        return secondMeet - firstMeet;
    }
    public int getLinkLen(ListNode root){
        Map<ListNode,Integer> map = new HashMap<>();
        int index = 0;
        while(!map.containsKey(root)){
            map.put(root,index++);
            root = root.next;
        }
        return index+1;
    }

    public boolean canAttendMeetings(int[][] intervals){
        Arrays.sort(intervals,(a,b) -> a[0] - b[0]);
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[i][0]<intervals[i-1][1]){
                return false;
            }
        }
        return true;
    }



}
