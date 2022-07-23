package everyDay;

import java.util.ArrayList;
import java.util.List;

public class GetFactors {
    /**
     * 找到   因数组合
     * 先找到最长的因数列表
     * 回溯是自下而上还是自上而下？
     * 从2开始判断，如果取余=0，纳入，继续对2取余数，直到不行，换3
     *
     *
     *
     *
     * @param n
     * @return
     */


    public List<List<Integer>> getFactors2(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if(n<=1){
            return res;
        }
        List<Integer> path0 = new ArrayList<>();
        int tmp = n;
        for(int i = 2; n >= (i * i) && tmp >1;){
            if(tmp%i == 0){
                path0.add(i);
                tmp = tmp/i;
            }else {
                ++i;
            }
        }
        if(path0.isEmpty()){
            return res;
        }
        //到这里，path0 收集到了所有的最小单位因数集合
        //接下来应该对这个因数集合进行排列组合
        //把因数两两组合---如何避免22222 - 4222-2422？
        //即：如何避免重复因数集合
        //方法一： Set去重？ ---  不太好
        //方案二:  一次挑两个，生成一个二阶因数set<>，一旦发现set里有了，那就挑另外两个
        //怎么都不行噢。因为不同的list源可能在进阶到下一层时，可能还会出现重复数字



        return res;
    }


    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = backTracing(2,n);
        return res;
    }

    public List<List<Integer>> backTracing(int startNum,int n){
        List<List<Integer>> res = new ArrayList<>();
        if(n<=1){
            return  res;
        }
        int qNum = (int)Math.sqrt(n);
        for (int i = startNum; i <= qNum; i++) {
            List<Integer> path = new ArrayList<>();
            if(n%i == 0 && n>1){
                int nextNum = n/i;
                path.add(i);
                path.add(nextNum);
                res.add(path);
                List<List<Integer>> tmpRes = backTracing(i,nextNum);
                for (List<Integer> tmpRe : tmpRes) {
                    tmpRe.add(startNum);
                    res.add(tmpRe);
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        GetFactors p = new GetFactors();
        System.out.println(p.getFactors(12));
    }


}
