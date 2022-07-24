package competitions.c_7_24;

import java.util.*;

/*
RANK--1995 SCORE 3+4+5  bug 3->2
排名1995，得分3+4+5 出错 第三题错误提交2次
力扣周赛 第303场
 */
public class Solution {
    public char repeatedCharacter(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)){
                return c;
            }
            else map.put(c,1);
        }
        return '0';
    }
    int ans = 0;
//    public int equalPairs(int[][] grid) {
//
//        /*
//        递归，一点一点匹配，把符合阶段性要求的纳入Set中
//         */
//        Set<Integer> tmpSet = new HashSet<>();
//        for (int i = 0; i < grid.length; i++) {
//            tmpSet.add(i);
//        }
//        for (int i = 0; i < grid.length; i++) {
//            dfs(grid, i, new HashSet<Integer>(tmpSet),0 );
//        }
//
//        return ans;
//    }
//    private void dfs(int[][] grid,int row, Set<Integer> set,int level){
//        if(level == grid.length || set.size()==0){
//            ans += set.size();
//            return;
//        }
////        Iterator<Integer> iterator = set.iterator();
////        while (iterator.hasNext()){
////            Integer next = iterator.next();
////            if(grid[row][level]!=grid[level][next]){
////                iterator.remove();
////            }
////        }
//        int[] tempArray = new int[set.size()];
//        int i =0;
//        for (Integer integer : set) {
//            tempArray[i] = integer;
//            i++;
//        }
//        for (int i1 = 0; i1 < tempArray.length; i1++) {
//            if(grid[row][level]!=grid[level][tempArray[i1]]){
//                set.remove(tempArray[i1]);
//            }
//        }
//
//        dfs(grid,row,set,level+1);
//    }
    public int equalPairs(int[][] grid) {
        int ans = 0;
        int n = grid.length;
        HashMap<String,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(grid[i][j]);
                sb.append(",");
            }
            String s = sb.toString();
            if(map.containsKey(s)){
                map.replace(s,map.get(s)+1);
            }else {
                map.put(s,1);
            }
        }

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(grid[j][i]);
                sb.append(",");
            }
            String s = sb.toString();
            if(map.containsKey(s)){
                ans += map.get(s);
            }
        }
        return ans;
    }

    public long countExcellentPairs(int[] nums, int k) {
        long ans = 0L;
        Set<Integer> noDuplicated = new HashSet<>();
        int[] bit1Num = new int[32];
        for (int num : nums) {
            // 去重
            if(!noDuplicated.contains(num)){
                int b1n = bit_1_nums(num);
                bit1Num[b1n]++;
            }
            noDuplicated.add(num);
        }
        // 有 i 个 1的数，与有 >=k - i 个 1 的数相结合，才能让 1 的数 >=k
        for (int i = 0; i < 32; i++) {
            int factori = bit1Num[i];
            for (int j = k-i; j <32 ; j++) {
                int factorj = bit1Num[j];
                ans += factorj * factori*1L;
            }
        }
        return ans;
    }
    private int bit_1_nums(int num){
        int bn = 0;
        while(num!=0){
            if((num & 1) ==1){
                bn++;
            }
            num >>=1;
        }
        return bn;
    }

    public static void main(String[] args) {
        Solution p = new Solution();
        int[] test = new int[]{
                1,2,3,1
        };
        int k = 3;
        p.countExcellentPairs(test,3);
    }
}
