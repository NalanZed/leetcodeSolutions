package backTracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GeneratePossibleNextMoves_293 {
    Set<String> res = new HashSet<>();
        public Set<String> generatePossibleNextMoves(String s) {
            Set<String> set = new HashSet<>();
            if (s == null || s.length() == 0) return set;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == '+' && s.charAt(i - 1) == s.charAt(i)) {
                    StringBuilder builder = new StringBuilder(s);
                    builder.replace(i - 1, i + 1, "--");
                    set.add(builder.toString());
                }
            }
            return set;
        }

    boolean win = false;
    //leetcode 294
    // 错误解答
    public boolean canWin(String currentState) {
        Set<String> resultSet = new HashSet<>();
        resultSet.add(currentState);
        recur(resultSet,false);
        return win;
    }
    //round表示回合，true表示自己的回合，false表示对手回合
    private void recur(Set<String> stringSet,boolean round){
        if(stringSet.isEmpty()){
            return;
        }
        Set<String> nextSet = new HashSet<>();
        for (String s : stringSet) {
            // 存在一种下法使得下一步绝杀则必胜了
            Set<String> nextResult = generatePossibleNextMoves(s);
            if(nextResult.isEmpty()){
                if(round){
                    win = true;
                    break;
                }
            }
            // 如果没有那么收集所有残局供对手选择
            for (String nextS : nextResult) {
                nextSet.add(nextS);
            }
        }
        // 如果没有赢，那就要让对手下一步，且不能形成必杀
        if(!win){
            recur(new HashSet<>(nextSet),!round);
        }
    }


    public static void main(String[] args) {
        GeneratePossibleNextMoves_293 p = new GeneratePossibleNextMoves_293();
        p.canWin("+++++");
    }
}
