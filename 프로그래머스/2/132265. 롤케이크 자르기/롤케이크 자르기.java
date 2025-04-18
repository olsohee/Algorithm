import java.util.*;

class Solution {
    public int solution(int[] topping) {
        
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        
        // 초기화
        for (int t : topping) {
            map2.put(t, map2.getOrDefault(t, 0) + 1);
        }
        
        // 각 구간을 자르기
        int answer = 0;
        for (int i = 0; i < topping.length; i++) {
            // 제거
            map2.put(topping[i], map2.get(topping[i]) - 1);
            if (map2.get(topping[i]) == 0) {
                map2.remove(topping[i]);
            }
            
            // 추가
            map1.put(topping[i], map1.getOrDefault(topping[i], 0) + 1);
            
            if (map1.keySet().size() == map2.keySet().size()) {
                answer++;
            }
        }
        
        return answer;
    }
}