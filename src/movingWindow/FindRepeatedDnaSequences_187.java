package movingWindow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。
 *     例如，"ACGAATTCCG" 是一个 DNA序列 。
 * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。
 */
public class FindRepeatedDnaSequences_187 {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        if(s.length()<=10){
            return res;
        }
        StringBuilder builder = new StringBuilder();
        Set<String> set = new HashSet<>();
        String substring = s.substring(0, 10);
        builder.append(substring);

        set.add(builder.toString());
        for(int i = 0,j =10;j<s.length();i++,j++){
            builder.deleteCharAt(0);
            builder.append(s.charAt(j));
            String tmp = builder.toString();
            if(!set.add(tmp)){
                if(!res.contains(tmp)){
                    res.add(tmp);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindRepeatedDnaSequences_187 p = new FindRepeatedDnaSequences_187();
        String test = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        p.findRepeatedDnaSequences(test);
    }

}
