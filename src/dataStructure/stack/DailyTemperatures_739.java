package dataStructure.stack;

import java.util.Stack;

public class DailyTemperatures_739 {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int[] res = new int[temperatures.length];
        for (int i = 1; i < temperatures.length; i++) {
            if(stack.isEmpty() || temperatures[i]<=temperatures[stack.peek()]){
                stack.push(i);
            }else {
                while(!stack.isEmpty()){
                    int top = stack.peek();
                    if(temperatures[i]>temperatures[top]){
                        res[top] = i - top;
                        stack.pop();
                    }else {
                        break;
                    }
                }
                stack.push(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        DailyTemperatures_739 p = new DailyTemperatures_739();
        int[] ints = new int[]{
                73,74,75,71,69,72,76,73
        };
        p.dailyTemperatures(ints);

    }
}
