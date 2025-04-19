import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < str1.length() - 1; i++) {
            String substr = str1.substring(i, i + 2);
            if (substr.matches("[a-zA-Z]+")) {
                substr = substr.toLowerCase();
                map1.put(substr, map1.getOrDefault(substr, 0) + 1);
            }
        }
        
        for (int i = 0; i < str2.length() - 1; i++) {
            String substr = str2.substring(i, i + 2);
            if (substr.matches("[a-zA-Z]+")) {
                substr = substr.toLowerCase();
                map2.put(substr, map2.getOrDefault(substr, 0) + 1);
            }
        }
        
        int inter = 0; // 교집합
        int sum = 0; // 합집합
        
        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {
                inter += Math.min(map1.get(key), map2.get(key));
            }
        }
        
        for (String key : map1.keySet()) {
            sum += map1.get(key);
        }
        
        for (String key : map2.keySet()) {
            sum += map2.get(key);
        }
        
        
        double answer = 0;
        if (map1.size() == 0 && map2.size() == 0) {
            answer = 1;
        } else {
            answer = (double)inter / (sum - inter);
        }
        
        answer *= 65536;
        
        return (int)answer;
    }
}