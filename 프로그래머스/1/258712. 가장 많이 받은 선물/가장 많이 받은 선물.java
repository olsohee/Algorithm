import java.util.*;

class Solution {
    
    String[] friends;
    
    public int solution(String[] friends, String[] gifts) {
        
        this.friends = friends;
        
        // map 초기화
        int[][] map = new int[friends.length][friends.length];
        for (String str : gifts) {
            String name1 = str.split(" ")[0];
            String name2 = str.split(" ")[1];
            map[getIdx(name1)][getIdx(name2)]++;
        }
        
        // 선물지수 계산
        int[] giftScore = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            int giveCnt = 0;
            int getCnt = 0;
            for (int j = 0; j < friends.length; j++) {
                if (i == j) continue;
                giveCnt += map[i][j];
                getCnt += map[j][i];
            }
            giftScore[i] = giveCnt - getCnt;
        }
        
        // 다음 달에 받을 선물 수 계산
        int[] result = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                // i가 j에게 준 선물 수
                int cnt1 = map[i][j];
                // j가 i에게 준 선물 수
                int cnt2 = map[j][i];
                
                if (cnt1 > cnt2) {
                    result[i]++;
                } else if (cnt1 < cnt2) {
                    result[j]++;
                } 
                // 주고받은 수가 같거나, 주고받지 않아서 둘 다 0인 경우
                else {
                    if (giftScore[i] == giftScore[j]) continue;
                    if (giftScore[i] > giftScore[j]) {
                       result[i]++;
                    } else {
                       result[j]++;
                    }
                }
            }
        }
        
        // 선물을 가장 많이 받는 선물의 수 찾기
        int answer = 0;
        for (int cnt : result) {
            answer = Math.max(answer, cnt);
        }
        return answer;
    }
    
    public int getIdx (String name) {
        for (int i = 0; i < friends.length; i++) {
            if (friends[i].equals(name)) return i;
        }
        return -1;
    }
}