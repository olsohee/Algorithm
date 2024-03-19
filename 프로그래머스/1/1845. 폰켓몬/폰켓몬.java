import java.util.*;

class Solution {
    public int solution(int[] nums) {
        
        // Map에 종류 번호, 갯수 저장
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        // Map 돌면서 1개씩 채우기 (개수가 1 이상인 경우만)
        int sum = 0;
        Set<Integer> choiceNum = new HashSet<>();
        outer: while (true) {
            for (int num : map.keySet()) {
                
                if (sum >= nums.length / 2) {
                    break outer;
                }
            
                int cnt = map.get(num);
                if (cnt >= 1) {
                    sum++;
                    map.put(num, cnt - 1);
                    choiceNum.add(num);
                }
            }
        }
        
        return choiceNum.size();
    }
}