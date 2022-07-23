package stringAbout;

import java.util.LinkedList;

public class FindContestMatch_544 {
    
    public String findContestMatch(int n) {
        LinkedList<String> deque1 = new LinkedList<>();
        LinkedList<String> deque2 = new LinkedList<>();
        //初始化deque1
        for (int i = 1; i <= n; i++) {
            deque1.addLast(String.valueOf(i));
        }
        // 在两个双向队列中来回挑选。
        // 直到只剩下一个字符串，那就是ans
        LinkedList<String> chooseList,maker = null;
        while(deque1.size()+ deque2.size()>1){
             chooseList = deque1.size()>deque2.size()?deque1 : deque2;
             maker = deque1.size()>deque2.size()?deque2 : deque1;
             while(!chooseList.isEmpty()){
                 StringBuilder sb = new StringBuilder();
                 String stronger = chooseList.pollFirst();
                 String weaker = chooseList.pollLast();
                 sb.append("(");
                 sb.append(stronger);
                 sb.append(",");
                 sb.append(weaker);
                 sb.append(")");
                 maker.addLast(sb.toString());
             }
        }
        String  res = maker.getFirst();
        return res;
    }

    public static void main(String[] args) {
        FindContestMatch_544 p = new FindContestMatch_544();
        System.out.println(p.findContestMatch(8));
    }
}