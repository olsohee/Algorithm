import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }
        
        Map<String, Integer> discountMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            String goods = discount[i];
            discountMap.put(goods, discountMap.getOrDefault(goods, 0) + 1);
        }
        
        int answer = 0;
        for (int day = 0; day <= discount.length - 10; day++) { // day: 등록 날
            // 확인
            if (canAnswer(wantMap, discountMap)) {
                answer++;
            }
            
            // 이동
            String removeGoods = discount[day];
            discountMap.put(removeGoods, discountMap.get(removeGoods) - 1);
            if (day + 10 == discount.length) break;
            String addGoods = discount[day + 10];
            discountMap.put(addGoods, discountMap.getOrDefault(addGoods, 0) + 1);
        }
        
        return answer; // 회원 등록 일수 (불가능하면 0 반환)
    }
    
    private boolean canAnswer(Map<String, Integer> wantMap, Map<String, Integer> discountMap) {
        for (String goods : wantMap.keySet()) {
            int wantCnt = wantMap.get(goods);
            if (!discountMap.containsKey(goods)) {
                return false;
            }
            if (discountMap.get(goods) < wantCnt) {
                return false;
            }
        }
        return true;
    }
}