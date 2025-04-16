import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int n = gems.length;
        
        Set<String> gemSet = new HashSet<>();
        for (String gem : gems) {
            gemSet.add(gem);
        }
        int cnt = gemSet.size(); // 보석 종류의 수
        
        Map<String, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        map.put(gems[start], 1);
        
        int minDiff = Integer.MAX_VALUE;
        int[] answer = new int[2];
        while (start <= end && end < n) {
            // System.out.println(start + " ~ " + end);
            if (map.keySet().size() == cnt) {
                if (minDiff > end - start) {
                    minDiff = end - start;
                    answer = new int[]{start + 1, end + 1};
                }
                
                if(map.get(gems[start]) == 1) {
                    map.remove(gems[start]);
                } else {
                    map.put(gems[start], map.get(gems[start]) - 1);
                }
                
                start++;
            } else {
                end++;
                if (end < n) {
                    map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
                }
            }
        }
                   
        return answer;
    }
}