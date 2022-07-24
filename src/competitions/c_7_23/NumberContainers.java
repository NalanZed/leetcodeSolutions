package competitions.c_7_23;

import java.util.*;

public class NumberContainers {


    Map<Integer,Integer> indexValueMap;
    Map<Integer, Set<Integer>> valueIndexMap;
    Map<Integer,Integer> minIndexMap;

    public NumberContainers() {
        this.indexValueMap = new HashMap<>();
        this.valueIndexMap = new HashMap<>();
        this.minIndexMap = new HashMap<>();
    }
    public void change(int index, int number) {
        // 已经纳入了这个index，就需要修正
        if(indexValueMap.containsKey(index)){
            int oldNum = indexValueMap.get(index);
            // 去valueIndexMap中删掉老数字对应的索引
            valueIndexMap.get(oldNum).remove(index);
            // 如果被删除的是最小索引，要重置最小索引,修正minIndexMap
            if(minIndexMap.get(oldNum) == index){
                // 如果没了，就移除
                if(valueIndexMap.get(oldNum).size()==0){
                    minIndexMap.remove(oldNum);
                }else {
                    Set<Integer> indexes = valueIndexMap.get(oldNum);
                    int minIndex = Integer.MAX_VALUE;
                    for (int in : indexes) {
                        minIndex=Math.min(minIndex,in);
                    }
                    minIndexMap.put(oldNum,minIndex);
                }
            }
        }
        indexValueMap.put(index,number);
        if(valueIndexMap.containsKey(number)){
            valueIndexMap.get(number).add(index);
            if(!minIndexMap.containsKey(number) || index<minIndexMap.get(number)){
                minIndexMap.put(number,index);
            }
        }else {
            Set<Integer> indexes = new HashSet<>();
            indexes.add(index);
            valueIndexMap.put(number,indexes);
            minIndexMap.put(number,index);
        }
    }

    public int find(int number) {
        if(minIndexMap.containsKey(number)){
            return minIndexMap.get(number);
        }else {
            return -1;
        }
    }

    public static void main(String[] args) {
        NumberContainers p = new NumberContainers();
        System.out.println(p.find(10));
        p.change(2,10);
        p.change(1,10);
        p.change(2,20);
        p.find(10);
        p.change(1,10);
        System.out.println(p.find(20));
    }
}
