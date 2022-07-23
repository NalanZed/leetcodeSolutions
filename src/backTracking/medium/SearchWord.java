package backTracking.medium;

import java.util.List;

public class SearchWord {
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int colum = board[0].length;
        boolean[][] used = new boolean[rows][colum];

        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[0].length;j++){
                //如果字符串word中不存在棋盘中的字符，那么将该字符标记为已使用
                //这次并不能将所有的无用字符标记为used，因为当找到word的第一个字符后便会开始遍历
                //这么做可能时间上并不划算，因为找在word中的index也挺耗时间的
                if(word.indexOf(board[i][j]) == -1){
                    used[i][j] = true;
                }
                //因为找第一个字符与找其它的字符过程不同，第一个字符是必须遍历棋盘的，而其它字符则是通过回溯，所以才需要这样遍历一次。
                //从棋盘的第一个字符开始找第一个目标，找到则开始进行回溯递归
                //一次回溯递归失败仍然还在循环之中，会紧接着找下一个目标
                if(board[i][j] != word.charAt(0)){
                    continue;
                }
                used[i][j] = true;
                //从头字符位置开始回溯 i，j
                //下一个目标为word中的第二个字符，targetIndex = 1
                //一旦返回true，说明找到了
                if(backTracking(i,j,1,word,board,used)){
                    return true;
                }
                used[i][j] = false;
            }
        }
        //所有尝试失败返回false
        return false;
    }
    private boolean backTracking(int startRows, int startColum, int targetIndex, String word,char[][] board,boolean[][] used){
        //当targetIndex == word.length()时，说明正在试图找第word.length()个目标，而这个目标已经超出word长度
        //所以意味着，整个word中的字符都被找到了
        //返回true
        if(targetIndex == word.length()){
            return true;
        }

        //开始进行回溯过程

        //上一行有未探索的字符，则向上探索
        if(startRows - 1 >= 0 && !used[startRows -1][startColum] && board[startRows-1][startColum] == word.charAt(targetIndex)){
            used[startRows -1][startColum] = true;
            if(backTracking(startRows-1,startColum,targetIndex+1,word,board,used)){
                return true;
            }
            used[startRows -1][startColum] = false;
        }
        //下一行有未探索的字符，则向下探索
        if(startRows + 1 < board.length && !used[startRows+1][startColum] && board[startRows+1][startColum] == word.charAt(targetIndex)){
            used[startRows +1][startColum] = true;
            if(backTracking(startRows+1,startColum,targetIndex+1,word,board,used)){
                return true;
            }
            used[startRows +1][startColum] = false;
        }
        //左一列有未探索的字符，则向左探索
        if(startColum - 1 >= 0 && !used[startRows][startColum -1] && board[startRows][startColum-1] == word.charAt(targetIndex)){
            used[startRows][startColum-1] = true;
            if(backTracking(startRows,startColum-1,targetIndex+1,word,board,used)){
                return true;
            }
            used[startRows][startColum-1] = false;
        }
        //右一列有未探索的字符，则向右探索
        if(startColum + 1 < board[0].length && !used[startRows][startColum+1] && board[startRows][startColum +1] == word.charAt(targetIndex)){
            used[startRows][startColum +1] = true;
            if(backTracking(startRows,startColum +1,targetIndex+1,word,board,used)){
                return true;
            }
            used[startRows][startColum +1] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        SearchWord searchWord = new SearchWord();
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String word = "CCEA";
        boolean exist = searchWord.exist(board, word);
        System.out.println(exist);
    }


}
