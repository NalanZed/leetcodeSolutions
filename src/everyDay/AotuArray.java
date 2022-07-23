package everyDay;

import java.util.PriorityQueue;

/**
 * 凹凸数组
 */
public class AotuArray {
    /**
     * 基本思路:
     * 将原数组分成大数组和小数组两部分，长度相差不超过1
     * 组成新数组时，轮流从两个数组中取
     * 采用大小根堆分组
     * 小根堆根节点要大于任何大根堆元素
     */
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public AotuArray(){
        left = new PriorityQueue<>((a,b)-> b - a);
        right = new PriorityQueue<>();
    }

    public void solution(int[] arry){
        int len = arry.length;
        for (int i = 0; i < arry.length; i++) {
            putInQueue(arry[i]);
        }
        int j = j = (len % 2 == 0) ? (len -1) : (len - 2);
        for (int i = 0, k = 0 ; i < len ;i++) {
            if(i % 2 == 0){
                arry[k] = left.poll();
                k = k + 2;
                System.out.println(arry[k]);
            }else {
                arry[j] = right.poll();
                j = j - 2;
//                arry[j]
            }
        }
    }

    private void putInQueue(int i) {
        if(left.size() == right.size()){
            // 两个堆中元素个数相同时，只要新元素i不大于小根堆根节点，可以入left
            if( right.size() == 0 || i <= right.peek()) {
                left.offer(i);
            }else {
                left.offer(right.poll());
                right.offer(i);
            }
        }
        else {
            if( i >= left.peek()){
                right.offer(i);
            }else {
                right.offer(left.poll());
                left.offer(i);
            }
        }
    }

    public static void main(String[] args) {
        int[] test = {1,1,2,1,2,2,1};
        AotuArray aotuArray = new AotuArray();
        aotuArray.solution(test);
    }
}
