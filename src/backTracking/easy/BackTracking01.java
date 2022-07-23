package backTracking.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通的组合问题
 */
public class BackTracking01 {

    List<List<Integer>> resultSet = new ArrayList<>();

    public List<List<Integer>> getResultSet() {
        return resultSet;
    }

    public void setResultSet(List<List<Integer>> resultSet) {
        this.resultSet = resultSet;
    }

    public void backTracking(int n, int k, int startIndex, List<Integer> path){
        //收集结果
        if(path.size() == k){
            resultSet.add(new ArrayList<>(path));
            return;
        }
        for(int i = startIndex; i <= n; i++){
            path.add(i);
            backTracking(n,k,i+1,path);
            path.remove(path.size()-1);
        }
    }




    public static void main(String[] args) {
        BackTracking01 backTracking01 = new BackTracking01();
        backTracking01.backTracking(5,2,1,new ArrayList<>());
        System.out.println("backTracking01.getResultSet() = " + backTracking01.getResultSet());
    }
}
