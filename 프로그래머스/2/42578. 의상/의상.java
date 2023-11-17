import java.util.*;
class Solution {
    public int solution(String[][] clothes) {

        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < clothes.length; i++) {
            String[] clothe = clothes[i];
            map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1);
        }

        int result = 1;
        for(String key : map.keySet()) {
            result *= map.get(key) + 1;
        }

        return result - 1;
    }
}