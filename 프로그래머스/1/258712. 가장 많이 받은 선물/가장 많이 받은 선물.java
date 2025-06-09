import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Integer> map = new HashMap<>(); // 이름 인덱스
        for (int i = 0; i < friends.length; i++) {
            map.put(friends[i], i);
        }
        
        int[][] chart = new int[friends.length][friends.length]; // 주고받은 내역
        int[] score = new int[friends.length]; // 선물지수
        
        for (String gift : gifts) {
            String sender = gift.split(" ")[0];
            String receiver = gift.split(" ")[1];
            chart[map.get(sender)][map.get(receiver)]++;
        }
        for (int i = 0; i < friends.length; i++) {
            int giveSum = 0;
            int receiveSum = 0;
            for (int j = 0; j < friends.length; j++) {
                giveSum += chart[i][j];
                receiveSum += chart[j][i];
            }
            score[i] = giveSum - receiveSum;
        }
        
        // 선물 계산
        int[] result = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                // i와 j 두 사람간 주고받을 선물 계산
                if (chart[i][j] > chart[j][i]) {
                    result[i]++;
                } else if (chart[i][j] < chart[j][i]){
                    result[j]++;
                } else {
                    if (score[i] > score[j]) {
                        result[i]++;
                    } else if (score[i] < score[j]) {
                        result[j]++;
                    }
                }
            }
        }
        
        Arrays.sort(result);
    
        return result[result.length - 1];
    }
}