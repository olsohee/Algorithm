import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        int[] giftScore = new int[n];
        int[][] record = new int[n][n];
        Map<String, Integer> idxMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            idxMap.put(friends[i], i);
        }
        
        // 주고받은 개수 계산
        for (String str : gifts) {
            String giver = str.split(" ")[0];
            String receiver = str.split(" ")[1];
            record[idxMap.get(giver)][idxMap.get(receiver)]++;
        }
        
        // 선물지수 계산
        for (int i = 0; i < n; i++) {
            int giveCnt = 0;
            int receiveCnt = 0;
            for (int j = 0; j < n; j++) {
                giveCnt += record[i][j];
                receiveCnt += record[j][i];
            }
            giftScore[i] = giveCnt - receiveCnt;
        }
        
        // 다음 달 계산
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (record[i][j] == record[j][i]) {
                    if (giftScore[i] > giftScore[j]) {
                        result[i]++;
                    } else if (giftScore[i] < giftScore[j]) {
                        result[j]++;
                    }
                } else if (record[i][j] > record[j][i]) {
                    result[i]++;
                } else if (record[i][j] < record[j][i]) {
                    result[j]++;
                } 
            }
        }
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, result[i]);
        }
        return answer;
    }
}