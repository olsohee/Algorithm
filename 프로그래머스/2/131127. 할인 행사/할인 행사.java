import java.util.*;

class Solution {
    
    int answer = 0;
    Map<String, Integer> map = new HashMap<>();
    String[] want;
    int[] number;
    
    public int solution(String[] want, int[] number, String[] discount) {
        this.want = want;
        this.number = number;
        
        for (int i = 0; i < 10; i++) {
            map.put(discount[i], map.getOrDefault(discount[i], 0) + 1);
        }
        
        compare();
        
        int start = 1;
        while (start + 9 < discount.length) {
            map.put(discount[start - 1], map.get(discount[start - 1]) - 1);
            map.put(discount[start + 9], map.getOrDefault(discount[start + 9], 0) + 1);
            
            compare();
            
            start++;
        }
        
        return answer;
    }
    
    public void compare() {
        for (int i = 0; i < want.length; i++) {
            if (!map.containsKey(want[i])) {
                return;
            }
            if (map.get(want[i]) < number[i]) {
                return;
            }
        }
        answer++;
    }
}