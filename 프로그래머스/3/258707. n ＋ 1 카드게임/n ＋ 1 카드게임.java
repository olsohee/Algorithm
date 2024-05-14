import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int idx = cards.length / 3;
        int target = cards.length + 1;
        Set<Integer> myCards = new HashSet<>();
        Set<Integer> additionalCards = new HashSet<>();
        
        for (int i = 0; i < idx; i++) {
            myCards.add(cards[i]);
        }
        
        while (true) {
            answer++;
            
            // 추가로 뽑을 카드가 없으면 끝내기
            if (idx >= cards.length) {
                break;
            }
            
            additionalCards.add(cards[idx]);
            additionalCards.add(cards[idx + 1]);
            idx += 2;
            
            boolean flag = false;
            
            // 1. myCards 에서만 2개 뽑을 수 있나 확인
            for (int num : myCards) {
                if (myCards.contains(target - num)) {
                    myCards.remove(num);
                    myCards.remove(target - num);
                    flag = true;
                    break;
                }
            }
            
            // 2. myCards 에서 1개, additionalCards 에서 1개 뽑을 수 있나 확인
            if (!flag && coin >= 1) {
                for (int num : myCards) {
                    if (additionalCards.contains(target - num)) {
                        myCards.remove(num);
                        additionalCards.remove(target - num);
                        flag = true;
                        coin--;
                        break;
                    }
                }
            }

            // 3. additionalCards 에서 2개 뽑을 수 있나 확인
            if (!flag && coin >= 2) {
                for (int num : additionalCards) {
                    if (additionalCards.contains(target - num)) {
                        additionalCards.remove(num);
                        additionalCards.remove(target - num);
                        flag = true;
                        coin -= 2;
                        break;
                    }
                }
            }
            
            // 2개를 뽑을 수 없으면 끝내기
            if (!flag) {
                break;
            }
        }
        
        return answer;
    }
}