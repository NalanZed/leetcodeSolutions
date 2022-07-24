package competitions.c_7_24;

import java.util.*;

public class FoodRatings {
    class FoodRate{
        public String name;
        public int rate;
        public FoodRate(String name,int rate){
            this.name = name;
            this.rate = rate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FoodRate foodRate = (FoodRate) o;
            return rate == foodRate.rate && Objects.equals(name, foodRate.name);
        }

        @Override
        public int hashCode() {
            return 0;
        }
        //        private void modifyRate(int newRate){
//            this.rate = newRate;
//        }
    }

    Map<String, TreeSet<FoodRate>> map;
    Map<String,Integer> foodNameRate;
    Map<String,String> foodNameCusin;
    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        TreeSet<FoodRate> treeSet = new TreeSet<>(new Comparator<FoodRate>() {
            @Override
            public int compare(FoodRate o1, FoodRate o2) {
                if(o1.rate>o2.rate){
                    return -1;
                }else if(o1.rate<o2.rate){
                    return  1;
                }else {
                    return o1.name.compareTo(o2.name);
                }
            }
        });
        foodNameRate = new HashMap<>();
        foodNameCusin = new HashMap<>();
        this.map = new HashMap<String, TreeSet<FoodRate>>();
        for (int i = 0; i < foods.length; i++) {
            String name = foods[i];
            String cuisin = cuisines[i];
            int rate = ratings[i];
            //不存在，就直接添加
            foodNameRate.put(name,rate);
            foodNameCusin.put(name,cuisin);
            if(!map.containsKey(cuisin)){
                treeSet.add(new FoodRate(name,rate));
                map.put(cuisin,new TreeSet<FoodRate>(treeSet));
                treeSet.clear();
            }else {
                map.get(cuisin).add(new FoodRate(name,rate));
            }
        }
    }

    public void changeRating(String food, int newRating) {
//        Integer index = foodNameIndex.get(food);
        String cusin = foodNameCusin.get(food);
        TreeSet<FoodRate> treeSet = map.get(cusin);
        treeSet.remove(new FoodRate(food,foodNameRate.get(food)));
        treeSet.add(new FoodRate(food,newRating));
        foodNameRate.put(food,newRating);
    }

    public String highestRated(String cuisine) {
        return map.get(cuisine).first().name;
    }
}
