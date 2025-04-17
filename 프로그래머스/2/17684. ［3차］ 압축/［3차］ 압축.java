import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<>();
        int num = 1;
        for (int i = 65; i <= 90; i++) {
            map.put(String.valueOf((char)i), num++);
        }
        
        int i = 0;
        List<Integer> list = new ArrayList<>();
        
        while (i < msg.length()) {
            String w = String.valueOf(msg.charAt(i));
            int lastIdx = i;
            String word = w;
            
            for (int j = i + 1; j < msg.length(); j++) {
                word += String.valueOf(msg.charAt(j));
                if (map.containsKey(word)) {
                    w = word;
                    lastIdx = j;
                }
            }
            
            // System.out.println(w);
            list.add(map.get(w));
            if (lastIdx + 1 < msg.length()) {
                map.put(w + String.valueOf(msg.charAt(lastIdx + 1)), num++);
            }
            i = lastIdx + 1;
        }
        
        int[] answer = new int[list.size()];
        for (int idx = 0; idx < list.size(); idx++) {
            answer[idx] = list.get(idx);
        }
        return answer;
    }
}