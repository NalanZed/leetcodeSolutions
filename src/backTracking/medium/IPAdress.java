package backTracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *     例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312"
 *     和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.'
 * 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 *
 */
public class IPAdress {
    List<String> resultSet = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        if(len<4 || len>12) {
            return resultSet;
        }
        int[] dotPlace ={
                0,0,0,0,0
        };
        backTracking(dotPlace,1,s,1);
        return resultSet;
    }
    //dotPalace数据结构，记录dot的位置
    //dotPlace类型为 int[4]，其中dot[0]默认为0
    //dotPalce【n】中的数字代表，第【n】个dot在字符串索引为n字符前方
    //所以，默认一个字符串的开头是.
    //dotNum初始值为0，curIndex初始值也为1，表示第一个字符
    void backTracking(int[] dotPlace,int curIndex,String s,int dotNum){
        //发现加入3个dot，最后还剩不到3位数
        if(dotNum == 5){
            //收集结果
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < dotPlace.length; i++) {
                    for(int j = dotPlace[i-1];j!=dotPlace[i];j++){
                        sb.append(s.charAt(j));
                    }
                    sb.append('.');
                }
                //将最后一个'.'删掉
                sb.deleteCharAt(sb.length()-1);
                resultSet.add(sb.toString());
            return;
        }
        //从第i个字符前开始打点尝试
        for(int i = curIndex; i <= s.length();i++){

            //发现剩余的位数超出平均每个dot后面不大于3个的要求时，要继续在这一位多走一些
            if(s.length()-i > (4-dotNum)*3 ){
                continue;
            }
            //如果该点与上一个点之间距离等于2，且上一个点后方的char为0时，要break,不允许前缀0
            if(i - dotPlace[dotNum-1] >= 2  && s.charAt(dotPlace[dotNum-1]) == '0'){
                break;
            }
            //如果该点与上一个点之间距离等于3，而第一个数字大于‘2’
            // 或第一个数字等于2且第二个数字大于5
            //或第一个数字等于5，第二个数字等于5，第三个数字大于5
            //都直接break;
            if(i - dotPlace[dotNum-1] == 3){
                if(s.charAt(dotPlace[dotNum-1]) - '3' >= 0){
                    break;
                }
                if(s.charAt(dotPlace[dotNum-1]) == '2' && s.charAt(dotPlace[dotNum-1]+1) - '6' >= 0){
                    break;
                }
                if(s.charAt(dotPlace[dotNum-1]) == '2' && s.charAt(dotPlace[dotNum-1]+1) == '5' && s.charAt(dotPlace[dotNum-1]+2) - '6' >=0){
                    break;
                }
            }
            //长度不能大于3
            if(i - dotPlace[dotNum-1] > 3){
                break;
            }
            dotPlace[dotNum] = i;
            backTracking(dotPlace,i+1,s,dotNum+1);
        }
    }

    public static void main(String[] args) {
        IPAdress ipAdress = new IPAdress();
        String s = "101023";
        List<String> strings = ipAdress.restoreIpAddresses(s);
        System.out.println("strings = " + strings);
    }
}
