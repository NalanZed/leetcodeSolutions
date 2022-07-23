import java.util.ArrayList;
import java.util.List;

// leetcode 281
public class ZigzagIterator {
    // 将所有向量纳入 datas中，统一管理
    public List<List<Integer>> datas;
    // position记录每个向量上的指针位置，如position[1] 的值，就是第二个向量当前要取值的位置
    private int[] positions;
    //count,记录元素总数，每取一个就减一
    private int count;
    private int countTmp;
    // switcher是选择器，选择到底选取哪一个向量
    private int switcher = -1;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2,List<Integer> v3) {
        datas = new ArrayList<>();
        datas.add(v1);
        datas.add(v2);
        datas.add(v3);
        for (List<Integer> data : datas) {
            count += data.size();
        }
        countTmp = count;
        positions = new int[datas.size()];
    }

    public int next() {
        int dataNum = datas.size();
        // switcher 作为选择器,换下一个向量
        switcher = (switcher + 1) % dataNum;
        // 如果某一个向量已经到达最顶端，就再次切换
        while(positions[switcher] >= datas.get(switcher).size()){
            switcher = (switcher + 1) % dataNum;
        }
        Integer res = datas.get(switcher).get(positions[switcher]);
        positions[switcher]++;
        return res;
    }

    public boolean hasNext() {
            if(--count >= 0){
                return true;
            }else {
                count = countTmp;
                positions = new int[datas.size()];
                switcher = -1;
                return false;
            }
    }

    public static void main(String[] args) {
        List<Integer> v1= new ArrayList<>();
        v1.add(1);v1.add(4);
        List<Integer> v2 = new ArrayList<>();
        v2.add(2);v2.add(5);v2.add(7);v2.add(8);
        List<Integer> v3 = new ArrayList<>();
        v3.add(3);v3.add(6);
        ZigzagIterator p = new ZigzagIterator(v1, v2,v3);
        while (p.hasNext()){
            System.out.println(p.next());
        }
//        while (p.hasNext()){
//            System.out.println(p.next());
//        }
    }
}
