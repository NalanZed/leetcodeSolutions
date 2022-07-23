package stringAbout;

import java.util.HashSet;
import java.util.Set;

public class StrToInt_offer67 {
    public int strToInt(String str) {
        Set<Character> dic = new HashSet<>();
        // 初始化dic
        char j ='0';
        for (int i = 0; i < 10; i++) {
            dic.add(j);
            j++;
        }
        dic.add('-');
        dic.add('+');
        // 记录 正负性
        boolean isPositive = false;
        // 找第一个合法字符
        // 记录第一个数字字符的index
        int legalIndex = -1;
        for (int i = 0; i < str.length(); i++) {
            //为 空 字符直接跳过
            if(str.charAt(i)==' '){
                continue;
            }
            char a = str.charAt(i);
            // 如果第一个字符不是dic中的字符，不需要转换
            if(!dic.contains(a)){
                return 0;
            }

            if(a == '-'){
                isPositive = false;
                legalIndex = i+1;
            }else if(a == '+'){
                isPositive = true;
                legalIndex = i+1;
            }else {
                isPositive = true;
                legalIndex = i;
            }
            break;
        }
        // 到达这里，应该已经找到了第一个合法字符位置
        // 如果legalIndex == -1  说明没有
        if(legalIndex == -1){
            return 0;
        }
        // 这里开始，合法字符只能是数字
        dic.remove('-');
        dic.remove('+');
        // 先确定极值
        int maxNum = Integer.MAX_VALUE / 10;

        //开始不断地累加整数，直到
        // 1. 越界
        // 2. 遇到非法字符
        // 3. 超过极值
        boolean crossStr = legalIndex < str.length() ? false : true;
        boolean illegalChar = false;
        boolean crossMax = false;
        int res = 0;

        while (!(crossStr || illegalChar || crossMax)){
            char nextChar = str.charAt(legalIndex);
            if(!dic.contains(nextChar)){
                illegalChar = true;
                continue;
            }
            int tmp = nextChar - '0';
            if( res > maxNum || (res == maxNum && tmp>7)) {
                res = isPositive ? Integer.MAX_VALUE:Integer.MIN_VALUE;
                crossMax = true;
                continue;
            }
            res = res * 10 + tmp;
            ++legalIndex;
            illegalChar = legalIndex < str.length() ? false : true;
        }
        if(!isPositive){
            res *= -1;
        }
        return res;
    }

    public static void main(String[] args) {
        StrToInt_offer67 p = new StrToInt_offer67();
        System.out.println(p.strToInt("2147483646"));
        return;
    }
}
