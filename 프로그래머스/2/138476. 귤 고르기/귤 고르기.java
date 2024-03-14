import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        // map에 담기 (key: 귤 넘버, value: 갯수)
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : tangerine) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        // map2에 갯수별로 귤 분류하기 (key: 갯수, value: 귤 넘버)
        Map<Integer, List<Integer>> map2 = new HashMap<>();
        for (Integer num : map.keySet()) {
            int cnt = map.get(num);
            List<Integer> list = map2.getOrDefault(cnt, new ArrayList<>());
            list.add(num);
            map2.put(cnt, list);
        }
        
        List<Integer> cntList = new ArrayList<>();
        for (int cnt : map2.keySet()) {
            cntList.add(cnt);
        }
        Collections.sort(cntList);
    
        // 갯수 많은 것부터 채우기
        int sum = 0;
        int answer = 0;
        for (int i = cntList.size() - 1; i >= 0; i--) {
            int cnt = cntList.get(i);
            
            for (int num : map2.get(cnt)) {
                if (sum < k) {
                    sum += cnt;
                    answer++;
                }
            }
            if (sum >= k) {
                break;
            }
        }
        
        return answer;
    }
}