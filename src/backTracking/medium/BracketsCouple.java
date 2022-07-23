package backTracking;

import java.util.ArrayList;
import java.util.List;

public class BracketsCouple {
    private List<String> ans = new ArrayList<>();

    private void backTracing(int n, int leftNum, int rightNum, StringBuilder sb){

        //收集结果
        if(leftNum == n && rightNum == n){
            ans.add(sb.toString());
            return;
        }
        //先选（，再选）
        if(leftNum < n){
            sb.append('(');
            leftNum ++ ;
            backTracing(n,leftNum,rightNum,sb);
            sb.deleteCharAt(sb.length()-1);
            leftNum--;
        }
        if(rightNum + 1 <= leftNum && rightNum < n){
            sb.append(')');
            rightNum ++ ;
            backTracing(n,leftNum,rightNum,sb);
            sb.deleteCharAt(sb.length()-1);
            rightNum--;
        }
        return;
    }


    public List<String> generateParenthesis(int n) {

        backTracing(n,0,0,new StringBuilder());

        return ans;
    }

    public static void main(String[] args) {
        BracketsCouple bracketsCouple = new BracketsCouple();
        List<String> strings = bracketsCouple.generateParenthesis(3);
        System.out.println("strings = " + strings);

    }
}
