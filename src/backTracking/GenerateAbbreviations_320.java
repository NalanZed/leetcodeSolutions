package backTracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateAbbreviations_320 {
    List<String> res = new ArrayList<>();
    public List<String> generateAbbreviations(String word) {
        for(int x=0;x < (1 << word.length()); x++){
            makeRes(word,x);
        }
        return  res;
    }
    private void makeRes(String word,int x){
        StringBuilder builder = new StringBuilder();
        int len = word.length();
        int k = 0; //k,表示需要变成数字的字符个数
        for(int i=0;i<len;x >>= 1,i++){
            // 掩码
            if((x & 1) == 0){
                if(k!=0){
                    builder.append(k);
                    k = 0; // k复位
                }
                builder.append(word.charAt(i));
            }else {
                // 累计数字个数
                ++k;
            }
        }
        if(k!=0){
            builder.append(k);
        }
        res.add(builder.toString());
    }
}
