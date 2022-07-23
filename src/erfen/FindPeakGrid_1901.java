package erfen;

public class FindPeakGrid_1901 {

    public int[] findPeakGrid(int[][] mat) {
        /**
         * 大致的思路是，
         * 二分选中间行，找到他的最大值，与上下两行的最大值作比较
         * 然后一直二分下去找二维的行峰值
         * 最后返回这个最大值就好
         * 因为这个峰值是行中的最大值，而这个峰值又比上下行的峰值都大
         * 所以它就是要找的答案
         *
         */
        int rowLen = mat.length;
        int up = 0,down = mat.length-1;
        int[] midMaxIndex = new int[2];
        while(up<=down){
            int mid = up + ((down-up)>>1);
            midMaxIndex = findMaxIndexInRow(mat,mid);
            // 比较mid上一行与mid行最大值
            if(mid != 0){
                int[] upMidMaxIndex = findMaxIndexInRow(mat,mid-1);
                //mid上一行的最大值大于本行最大值
                if(mat[midMaxIndex[0]][midMaxIndex[1]]<mat[upMidMaxIndex[0]][upMidMaxIndex[1]]){
                    // down上升至mid位置,并开始下一个二分
                    down = mid-1;
                    continue;
                }
            }
            // 如果上一行没有成功就比较下一行
            if(mid != rowLen-1){
                int[] downMidMaxIndex = findMaxIndexInRow(mat,mid+1);
                //mid上一行的最大值大于本行最大值
                if(mat[midMaxIndex[0]][midMaxIndex[1]]<mat[downMidMaxIndex[0]][downMidMaxIndex[1]]){
                    // up下降至mid位置,并开始下一个二分
                    up = mid+1;
                    continue;
                }
            }
            //上下的max都比mid小，那不就直接就返回本行了
            return midMaxIndex;
        }

        return midMaxIndex;
    }

    private int[] findMaxIndexInRow(int[][] mat, int row) {

        int tmp = 0;
        for (int i = 0; i < mat[row].length; i++) {
            if(mat[row][i]>mat[row][tmp]){
                tmp = i;
            }
        }
        return new int[]{row,tmp};
    }


}
