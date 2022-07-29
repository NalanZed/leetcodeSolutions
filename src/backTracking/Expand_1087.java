package backTracking;

import java.util.*;

public class Expand_1087 {
    ArrayList<String> ans = new ArrayList<>();
    public String[] expand(String s) {
        dfs(s,new StringBuilder(),0);
        Collections.sort(ans);
        String[] res = ans.toArray(new String[ans.size()]);
        return res;
    }

    private void dfs(String s,StringBuilder path,int startIndex){
        if(startIndex>=s.length()){
            String s1 = path.toString();
            ans.add(s1);
            return;
        }
        if(s.charAt(startIndex) == '{'){
            int right = startIndex;
            // 记录右括号位置
            while (s.charAt(right)!='}'){
                right++;
            }
            // 取{}里面的字符
            while(startIndex<right){
                // 跳过 { ，
                startIndex++;
                path.append(s.charAt(startIndex));
                dfs(s,path,right+1);
                path.deleteCharAt(path.length()-1);
                startIndex++;
            }
        }else {
            int left = startIndex;
            while(left<s.length() && s.charAt(left)!='{'){
                left++;
            }
            path.append(s.substring(startIndex,left));
            dfs(s,path,left);
            path.delete(path.length()-left+startIndex,path.length());
        }
    }

        public String[] expand_bfs(String s) {
            Queue<String> queue = new LinkedList<>();
            queue.add(s);
            int start = 0, end = 0;
            while (queue.peek().contains("{")) {
                String nowS = queue.poll();
                start = nowS.indexOf("{");
                end = nowS.indexOf("}");
                String[] temp = nowS.substring(start + 1, end).split(",");
                Arrays.sort(temp);
                for (int i = 0; i < temp.length; i++) {
                    queue.add(nowS.substring(0, start) + temp[i] + nowS.substring(end + 1));
                }
            }
            String[] result = new String[queue.size()];
            int k = 0;
            while (!queue.isEmpty()) {
                result[k] = queue.poll();
                k++;
            }
            return result;
        }





    public static void main(String[] args) {
        Expand_1087 p = new Expand_1087();
        String test = "{a,b}c{d,e}f";
        p.expand(test);
    }
}
