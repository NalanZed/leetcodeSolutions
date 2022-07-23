package everyDay;

import java.util.*;

public class HuiwenNum {
    /**
     * 判断数字是否中心对称？
     * 双指针向中间移动
     * @param num
     * @return
     */

    public boolean isStrobogrammatic(String num) {
       int len = num.length();
       if(len <= 1) {
           return true;
       }
       char[] nums = num.toCharArray();
       char[] dic = new char[]{
               '0','1','f','f','f','f','9','f','8','6'
       };

        for (int i = 0,j = len - 1; i <= j; ++i,--j) {
            char c1 = nums[i]; char c2 = nums[j];
            if (dic[c1 - '0'] == 'f' || dic[c1 - '0'] != c2 ) {
                return false;
            } else {
                continue;
            }
        }
        return true;
    }

    /**
     * 给一个 n ,返回长度为n的符合中心对称的数字组合
     *
     * n = 1 ------- “0”，“1”，“8”
     * n = 2 ------- "11","69","88","96"
     *
     * n = 1 和 n = 2 的结果与 此后的类推关系密切。
     * n = 1 的数，适合放在当n为奇数时的中心位置
     * n = 2 时，诸如 00因为不是数字而被省略，此时留下的四个数字是可以放心地放在外围的数字
     * n为奇数时，可以看作是在多个数对中心插入一个数字，所以他等于 f( n - 1 ) 每个数字的中间插入 f(1)
     * n 为 偶数时，等于 f(2) 内部插入 f(2+)
     * @param n
     * @return
     */
    public List<String> findStrobogrammatic(int n){
        List res = new ArrayList<String>();
        List<String> dic1 = new ArrayList<>();
        dic1.add("0");
        dic1.add("1");
        dic1.add("8");
        List<String> dic2 = new ArrayList<>();
        dic2.add("11");
        dic2.add("69");
        dic2.add("88");
        dic2.add("96");

        if(n == 1){
            return dic1;
        }else if(n == 2){
            return dic2;
            //n = 3 或者以上，需要开始插入
        }else {
            dic2.add("00");
            StringBuilder path0 = new StringBuilder();
            for (int i = 0; i < dic2.size()-1; i++) {
                //path0 是 11，69，88，96中的一个，作为数字的最边界
                //每次换一个边界再进入回溯
                path0.append(dic2.get(i));
                insertChars(res,n-2,path0,dic1,dic2);
                //讲path0 清空
                path0.deleteCharAt(path0.length()-1);
                path0.deleteCharAt(path0.length()-1);
            }
        }
        return res;
    }

    /**
     * 每次插入，都直接插在path上index = 1 和 path.size()-1 的位置
     * 此后回溯时，也要删除对应的位置上的符号
     * @param res       结果集
     * @param restNum   剩余需要插入的字符数
     * @param path      路径
     * @param dic1      单字符字典
     * @param dic2      双字符字典
     */
    private void insertChars(List res, int restNum, StringBuilder path, List<String> dic1, List<String> dic2) {
        // 收割结果
        if(restNum == 0){
            res.add(path.toString());
            return ;
        }
        boolean isOdd = (restNum % 2  == 1) ? true : false;
        int offset = 1;
        if(isOdd){
            for (int i = 0; i < dic1.size(); i++) {
                path.insert(offset,dic1.get(i));
                insertChars(res,restNum-1,path,dic1,dic2);
                path.deleteCharAt(offset);
            }
        }else {
            for (int i = 0; i < dic2.size(); i++) {
                //用包装类，方便转字符串
                Character l = dic2.get(i).charAt(0);
                Character r = dic2.get(i).charAt(1);
                // 只能insert字符串，所以这里只能转换成字符串了
                path.insert(offset,l.toString());
                path.insert(path.length()-1,r.toString());
                insertChars(res,restNum-2,path,dic1,dic2);
                path.deleteCharAt(offset);
                path.deleteCharAt(path.length()-2);
            }
        }
    }


    /**
     * 相比于数字的位数，这回是规定了范围
     * 【low,high】
     *
     * 思路一：
     * f(n) = 【0,n】 内中心对称数
     * f(high) - f(low)
     * 缺点： 相当于在做 [0,n]内有多少中心对称这个题
     *
     *
     * 思路二：
     * 转化为数学问题
     * A = f（n） ： 位数为 n 的数字中有 A 个 中心对称数
     * 当 n >= 3 时， f(n) = f(n-1)* ( n % 2 == 1 ? 3 : 5)
     *
     * B = l(n,x) : 数字 x 是 位数为 n 的中心对称数中的第几个（按照从小到大排序）
     * C = h(x) : 比数字 x 大的最小的 中心对称数（要求位数相同，要先和同位数最大值先判断一下）
     *
     *
     * @param low
     * @param high
     * @return
     */

    public int strobogrammaticInRange(String low, String high) {
        int res = 0;
        boolean isVL = isStrobogrammatic(low);
        int h = strobogrammaticInRangeSingle(high);
        int l = strobogrammaticInRangeSingle(low);
        if( isStrobogrammatic(low) && low.equals(high)){
            if(isVL){
                return 1;
            }
        }
        res = h - l;
        if(isVL) {
            res++;
        }
        return res;
    }
    public int strobogrammaticInRangeSingle(String s) {

        int oBit = s.length();
        int total = 0;
        for (int i = 1; i < oBit; i++) {
            total += findStrobogrammaticNum(i);
        }
        int offset = offset(s);
        return offset+total;
    }
    public int findStrobogrammaticNum(int oBit){
        int res = 1;
        if(oBit <= 0){
            return 1;
        }
        if(oBit == 1){
            return 3;
        }else if(oBit == 2){
            return 4;
        }
        boolean isOdd = oBit % 2 == 1 ? true : false;
        if(isOdd){
            res = 3;
            --oBit;
        }
        res *= 4;
        for(int i = 2;i < oBit; i=i+2){
            res *= 5;
        }
        return res;
    }


    public int offsetLower2(String num){
        int res = 0;
        int oBit = num.length();
        Integer a = Integer.valueOf(num);
        // 两位数要特殊处理
        if(oBit == 2){
            if(a < 11){
                return 0;
            }else if(a < 69){
                return 1;
            }else if(a < 88){
                return 2;
            }else if(a < 96){
                return 3;
            } else{
                return 4;
            }
        }
        if(oBit == 1){
            if(a < 0){
                return 0;
            }else if(a < 1){
                return 1;
            }else if(a < 8){
                return 2;
            } else{
                return 3;
            }
        }
        return res;
    }

    /**
     * num.length
     * @param num
     * @return
     */
    public int offset(String num){
        // 数字位数
        int oBit = num.length();
        if(oBit<3){
            return offsetLower2(num);
        }

        int res = 0;

        //索引表示位数上的数字，值为其对应deta值（偶数时）
        int[] detaN = new int[]{
                0,1,2,2,2,2,2,3,3,4
        };
        // 索引表示位数上的数字，值为其对应deta值（奇数时）
        int[] detaM = new int[]{
                0,1,2,2,2,2,2,2,2,3
        };
        Set<Character> set = new HashSet<>();
        set.add('0'); set.add('1'); set.add('6'); set.add('8'); set.add('9');
        //第一位要特殊处理 deta值要减一
        char first = num.charAt(0);
        int gap = first - '0';
        int detaFirst = detaN[gap] - 1;
        int lastoBits = oBit -2;
        int muti = offsetHelper(lastoBits);
        res += detaFirst * muti;

        for(int i=1;lastoBits > 1;i++){
            if(!set.contains(num.charAt(i-1))){
                return res;
            }
            int d = num.charAt(i) - '0';
            int deta = detaN[d];
            // deta * f(n-1)
            int addNum = deta * offsetHelper(lastoBits-2);
            res += addNum;
            lastoBits = lastoBits - 2;
        }
        if(lastoBits==1){
            if(lastoBits == 1){
                int midBit = oBit / 2;
                int c = num.charAt(midBit) - '0';
                int factor = detaM[c];
                int addNum = factor;
                res += addNum;
            }
        }
        if(needPlus(num)){
            ++res;
        }
        return res;
    }


    public boolean needPlus(String num){

        Map<Character,Character> map = new HashMap<>();
        map.put('0','0'); map.put('1','1');map.put('6','9');
        map.put('8','8'); map.put('9','6');

        Set<Character> set = new HashSet<>();
        set.add('0'); set.add('1'); set.add('6'); set.add('8'); set.add('9');
        int len = num.length();
        boolean isOdd = (len % 2 == 1);

        int mid = isOdd ? (len+1)/2 : (len/2);
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<mid;i++){
            Character c = num.charAt(i);
            if(!set.contains(c)){
                return false;
            }
            sb.append(c);
        }
        int startIndex = isOdd ? (mid-2) : (mid-1);
        for(int i= startIndex; i>=0;--i){
            sb.append(map.get(num.charAt(i)));
        }
        String nearests = sb.toString();
        Long nearestNum = Long.valueOf(nearests);
        Long tmp = Long.valueOf(num);
        if(tmp >= nearestNum){
            return true;
        }else {
            return false;
        }
    }


    private int offsetHelper(int lastoBits) {
        int res = 1;
        if(lastoBits == 0){
            return 1;
        }
        if(lastoBits == 1){
            res = 3;
        }else if(lastoBits == 2){
            res = 5;
        }else {
            for(; lastoBits > 1;){
                res *= 5;
                lastoBits = lastoBits-2;
            }
            if(lastoBits == 1){
                res *= 3;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        HuiwenNum h = new HuiwenNum();
//        System.out.println(h.needPlus("1881"));
//        for (int i = 0; i < 10; i++) {
//            String s = String.valueOf(i);
//            System.out.println(h.offset(s));
//        }
//        System.out.println(h.offset("100"));
//        int i = h.strobogrammaticInRange("11", "100");
//        System.out.println(i);
//        System.out.println(h.strobogrammaticInRangeSingle("1001"));
        System.out.println(h.strobogrammaticInRangeSingle("100000000000000"));

//        System.out.println(h.isStrobogrammatic("121"));


    }



//    /**
//     * 计算数字 x 的位数
//     * 传进来的参数是字符串，这个可以免了
//     * @param x
//     * @return
//     */
//    public int calculateObit(int x){
//
//        int oBit = 0;
//        if(x == 0) {
//            oBit = 1;
//        }else {
//            while(x>0){
//                x /= 10;
//                oBit++;
//            }
//        }
//        return oBit;
//    }

    /**
     * 计算 oBit 位数 含有Strobogrammatic数的个数
     * @return
     */

    /**
     * 给定 数字n 及其位数oBit(>=3)
     * 返回 n是 oBit 位数中第 int 个 Strobogrammatic数字
     * @return
     */
//    public int findNumOffset(String num){
//        int res = 0;
//        //索引表示位数上的数字，值为其对应deta值（偶数时）
//        int[] detaN = new int[]{
//            0,1,2,2,2,2,2,3,3,4
//        };
//        // 索引表示位数上的数字，值为其对应deta值（奇数时）
//        int[] detaM = new int[]{
//            0,1,2,2,2,2,2,2,2,3
//        };
//        // 数字位数
//        int oBit = num.length();
//
//
//        //第一位要特殊处理 deta值要减一
//        char first = num.charAt(0);
//        int gap = first - '0';
//        int detaFirst = detaN[gap] - 1;
//        int lastoBits = oBit -2;
//        int firstNum = findStrobogrammaticNum(lastoBits) * detaFirst;
//        res += firstNum;
//        for(int i=1;lastoBits > 2;i++){
//            int d = num.charAt(i) - '0';
//            int factor = detaN[d];
//            // deta * f(n-1)
//            int addNum = factor * findStrobogrammaticNum(lastoBits-2);
//            res += addNum;
//            lastoBits = lastoBits-2;
//        }
//        if(lastoBits == 1){
//            int midBit = oBit / 2;
//            int c = num.charAt(midBit) - '0';
//            int factor = detaM[c];
//            int addNum = factor;
//            res += addNum;
//        }
//        if(lastoBits == 2){
//            int midBit = oBit / 2;
//            int c = num.charAt(midBit) - '0';
//            int factor = detaN[c];
//            int addNum = factor;
//            res += addNum;
//        }
//        if(num.charAt(0)-num.charAt(num.length()-1)<=0 || (num.charAt(0)=='9' && num.charAt(num.length()-1) =='6')){
//            ++res;
//        }
//        return res;
//    }

}
