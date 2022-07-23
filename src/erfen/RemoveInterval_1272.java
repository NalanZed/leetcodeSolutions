package erfen;

import java.util.ArrayList;
import java.util.List;

public class RemoveInterval_1272 {
//    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
//        int leftNum = toBeRemoved[0],rightNum = toBeRemoved[1];
//        int rowLen = intervals.length;
//        // 记录需要被删除的区间坐标
//        int[] leftRemoveP = binarySearch(intervals,leftNum);
//        //确定开始删除的行
//        int startRow = 0;
//        if(leftRemoveP[0] != -1 ){
//            if(leftRemoveP[1] == 1) startRow = leftRemoveP[0] + 1;
//            else startRow = leftRemoveP[0];
//        }
//        List<List<Integer>> res = new ArrayList<>();
//        int curRow = 0;
//        // 起点前的区间可以直接纳入
//        while(curRow<startRow){
//            List<Integer> path = new ArrayList<>();
//            path.add(intervals[curRow][0]);path.add(intervals[curRow][1]);
//            res.add(path);
//            curRow++;
//        }
//        if(intervals[curRow][0] == leftNum){
//            curRow++;
//        }else if(intervals[curRow][1] < leftNum){
//            // 分裂区间
//            curRow++;
//        }else {
//            List<Integer> path = new ArrayList<>();
//            path.add(leftNum);path.add(intervals[curRow][1]);
//            curRow++;
//        }
//        while(intervals[curRow][1]<=rightNum){
//            curRow++;
//        }
//        if(curRow)
//
//
//
//        return null;
//    }
public List<List<Integer>> removeInterval_cyy(int[][] intervals, int[] toBeRemoved) {
    int n = intervals.length;
    int m = intervals[0].length;
    int x = toBeRemoved[0];
    int y = toBeRemoved[1];
    List<List<Integer>> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        if (intervals[i][0] < x){//开始区间在删除区间前
            List<Integer> li = new ArrayList<>();
            li.add(intervals[i][0]);
            li.add(Math.min(intervals[i][1],x));
            list.add(li);
        }
        if (intervals[i][0] < y && intervals[i][1] > y){//开始区间在删除区间前，结束在删除区间后
            List<Integer> li = new ArrayList<>();
            li.add(y);
            li.add(intervals[i][1]);
            list.add(li);
        }
        if (intervals[i][0] > y){//区间在删除区间后
            List<Integer> li = new ArrayList<>();
            li.add(intervals[i][0]);
            li.add(intervals[i][1]);
            list.add(li);
        }
    }
    return list;
}
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved){



        return null;
    }



    private int[] binarySearch(int[][] intervals,int target){
        int row,col;
        int l = -1,r=intervals.length*2;
        // 红蓝二分，l指向最接近target的，小于等于leftNum的坐标
        while(l+1<r){
            int mid = r + ((r - l)>>1);
            int rIndex = mid / 2;
            int cIndex = mid % 2;
            if(intervals[rIndex][cIndex] <= target){
                l= mid;
            }else {
                r = mid;
            }
        }
        //说明第一个左区间都比leftNum大
        if(l==-1){
            row = -1;
            col = -1;
        }else {
            row = l / 2;
            col = l % 2;
        }
        return new int[]{row,col};
    }
}
