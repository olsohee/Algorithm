import java.util.*;

class Solution {
    
    int coin;
    int[] cards;
    int n;
    Set<Integer> myCardSet = new HashSet<>();
    Set<Integer> additionalCardSet = new HashSet<>();
    
    public int solution(int coin, int[] cards) {
        this.coin = coin;
        this.cards = cards;
        n = cards.length;
        for (int i = 0; i < n / 3; i++) {
            myCardSet.add(cards[i]);
        }
        
        int answer = 0;
        int idx = n / 3;
        
        while (true) {
            answer++;
            
            // 뽑을 카드가 없는 경우 -> 끝내기
            if (idx >= n) {
                break;
            }
            
            // 2개 뽑기
            additionalCardSet.add(cards[idx++]);
            additionalCardSet.add(cards[idx++]);
            
            // 이미 가진 카드에서 낼 수 있으면 내기
            boolean flag = false;
            for (int card : myCardSet) {
                if (myCardSet.contains(n + 1 - card)) {
                    myCardSet.remove((Object) card);
                    myCardSet.remove((Object) (n + 1 - card));
                    flag = true;
                    break;
                }
            }
            
            // 그렇지 않으면 additional과 섞어서 내기 (coin 감소)
            if (!flag && coin >= 1) {
                // myCardSet에서 1개, additionalCardSet에서 1개
                for (int card : myCardSet) {
                    if (additionalCardSet.contains(n + 1 - card)) {
                        myCardSet.remove((Object) card);
                        additionalCardSet.remove((Object) (n + 1 - card));
                        coin--;
                        flag = true;
                        break;
                    }
                }
            }
            
            if (!flag && coin >= 2) {
                // additionalCardSet에서 2개
                for (int card : additionalCardSet) {
                    if (additionalCardSet.contains(n + 1 - card)) {
                        additionalCardSet.remove((Object) card);
                        additionalCardSet.remove((Object) (n + 1 - card));
                        coin -= 2;
                        flag = true;
                        break;
                    }
                }
            }
            
            // n + 1에 맞춰서 카드를 낼 수 없는 경우 -> 끝내기
            if (!flag) {
                break;
            }
        }
        
        return answer;
    }
}