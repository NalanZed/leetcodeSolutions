import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;



class Solution {
    static String s = "leetcode";
    static public char firstUniqChar(String s) {
        Map<Character,Boolean> dic = new LinkedHashMap<>();
        char[] chars = s.toCharArray();
        for(char ch : chars){
            dic.put(ch,!dic.containsKey(ch));
        }
        for(Map.Entry<Character, Boolean> entry : dic.entrySet()) {
            if(entry.getValue()) {
                return entry.getKey();
            }
        }
        return ' ';
    }

    public long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        /*
        贪心找最大串，然后再去用数学算
         */
        // key是最大连续0长度，value是它出现了多少次
        Map<Integer,Integer> map = new HashMap<>();
        int tmpCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==0){
                do{
                    tmpCount++;
                    i++;
                }while(i < nums.length && nums[i]==0);
                if(!map.containsKey(tmpCount)){
                    map.put(tmpCount,1);
                }else {
                    map.put(tmpCount,map.get(tmpCount) + 1);
                }
                tmpCount = 0;
            }
        }
        for (Integer key : map.keySet()) {
            int times = map.get(key);
            long timesL = Long.valueOf(times);
            long keyL = Long.valueOf(key);
            long tmp = (keyL * (keyL+1) / 2) * timesL;
            ans += tmp;
        }
        return ans;
    }

    public String bestHand(int[] ranks, char[] suits) {
        boolean Flush = false;
        int count = 1;
        // 记录13种大小的数量
        int[] c = new int[14];
        c[ranks[0]-'a']++;
        for (int i = 1; i < suits.length; i++) {
            c[ranks[i] - 'a']++;
            if(suits[i] == suits[i-1]){
                count++;
            }
        }
        if(count == 5){
            return "Flush";
        }
        int maxNum = 0;
        for (int i = 0; i < c.length; i++) {
            maxNum = Math.max(maxNum,c[i]);
        }
        if(maxNum>2){
            return "Three of a Kind";
        }else if(maxNum == 2){
            return "Pair";
        }else return "High Card";
    }

    public static void main(String[] args) {
        int[] test = new int[]{
                1,3,0,0,2,0,0,4
        };
        Solution solution = new Solution();
        System.out.println(solution.zeroFilledSubarray(test));
    }
}