package backTracking;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CombinedSum {

    private List<List<Integer>> resultSet = new ArrayList<>();

    public List<List<Integer>> getResultSet() {
        return resultSet;
    }

    public void setResultSet(List<List<Integer>> resultSet) {
        this.resultSet = resultSet;
    }


    public void backTraking(int sum, int[] candidates, int startIndex, List<Integer> path){
        if(sum > 8){
            return;
        }
        if(sum == 8){
            resultSet.add(new ArrayList<Integer>(path));
            return;
        }
        for(int i = startIndex;i < candidates.length;i++ ){
            if(sum + candidates[i] > 8){
                break;
            }
            //把自己加入path的同时，要累积一下sum
            path.add(candidates[i]);
            sum += candidates[i];

            backTraking(sum,candidates,i,path);

            //回溯，要剪掉之前加入的candidate[i]
            path.remove(path.size()-1);
            sum -= candidates[i];
        }
    return;
    }

    public static void main(String[] args) {
        int[] candidates = new int[3];
        candidates[0] = 2;
        candidates[1] = 3;
        candidates[2] = 5;
        int target = 8;
        Arrays.sort(candidates);
        CombinedSum combinedSum = new CombinedSum();
        combinedSum.backTraking(0,candidates,0,new ArrayList<Integer>());
        System.out.println("combinedSum.getResultSet() = " + combinedSum.getResultSet());
    }
}
