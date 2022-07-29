package priorityQueue;

import java.util.PriorityQueue;

public class ConnectSticks_1167 {
    public int connectSticks(int[] sticks) {
        int ans = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < sticks.length; i++) {
            priorityQueue.offer(sticks[i]);
        }
        while(priorityQueue.size()>1){
            int a = priorityQueue.poll();
            int b = priorityQueue.poll();
            int newStick = a + b;
            ans += newStick;
            priorityQueue.offer(newStick);
        }
        return ans;
    }
}
