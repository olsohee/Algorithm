import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        
        List<String> list = new LinkedList<>();
        int answer = 0;
        
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            if (list.contains(city)) { // 히트
                list.remove(city);
                list.add(city);
                answer += 1;
            } else { // 미스
                if (list.size() == cacheSize) {
                    list.remove(0);
                    list.add(city);
                } else {
                    list.add(city);
                }
                answer += 5;
            }
        }
       
        return answer;
    }
}