package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindSmallestRegion_1257 {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String,List<String>> parentMap = new HashMap<>();
        List<String> grandParent = new ArrayList<>();
        for (List<String> region : regions) {
            String parent = region.get(0);
            // 特殊处理第一个节点
            if(!parentMap.containsKey(parent)){
                parentMap.put(parent, new ArrayList<>());
            }else {
                // 祖父
                grandParent = parentMap.get(parent);
            }
            for (int i = 1; i < region.size(); i++) {
                // parentMap中已经有子region了，且子region的父regionList没有包含parent
                if(parentMap.containsKey(region.get(i)) && !parentMap.get(region.get(i)).contains(parent)){
                    parentMap.get(region.get(i)).add(parent);
                    for (String ss : grandParent) {
                        parentMap.get(region.get(i)).add(ss);
                    }
                }else if(!parentMap.containsKey(region.get(i))){
                    List<String> parentList = new ArrayList();
                    parentList.add(parent);
                    for (String ss : grandParent) {
                        parentList.add(ss);
                    }
                    parentMap.put(region.get(i),parentList);
                }
            }
        }
        // 找到region1 和 region2 共同的父节点
        List<String> region1Parents = parentMap.get(region1);
        List<String> region2Parents = parentMap.get(region2);
        String res = "";
        if(region1Parents.contains(region2)){
            return region2;
        }else if(region2Parents.contains(region1)){
            return region1;
        }
        for (int i = 0; i < region1Parents.size(); i++) {
            String parent1 = region1Parents.get(i);

            if(region2Parents.contains(parent1)){
                if(res=="" || parentMap.get(parent1).contains(res)){
                    res = parent1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<String>> test = new ArrayList<>();




    }
}
