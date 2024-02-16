import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {

        int[][] map = new int[friends.length][friends.length];
        int[] giftScore = new int[friends.length];
        Map<String, Integer> hashMap = new HashMap<>();

        for (String str : gifts) {
            hashMap.put(str, hashMap.getOrDefault(str, 0) + 1);
        }

        // map 채우기
        for (String key : hashMap.keySet()) {
            String from = key.split(" ")[0];
            String to = key.split(" ")[1];
            int fromIdx = -1;
            int toIdx = -1;
            for (int i = 0; i < friends.length; i++) {
                if (friends[i].equals(from)) fromIdx = i;
                if (friends[i].equals(to)) toIdx = i;
            }
            map[fromIdx][toIdx] = hashMap.get(key);
        }

        // 선물 지수 계산
        for (int i = 0; i < friends.length; i++) {
            int giveSum = 0;
            int getSum = 0;
            for (int j = 0; j < friends.length; j++) {
                giveSum += map[i][j];
                getSum += map[j][i];
            }
            giftScore[i] = giveSum - getSum;
        }

        // 다음 달에 받을 선물 개수 계산
        int[] nextMonth = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                int diff = map[i][j] - map[j][i];
                if (diff > 0) { // i가 더 많이 줬으면, i가 받기
                    nextMonth[i]++;
                } else if (diff == 0) { // 같으면, 선물 지수가 많은 사람이 받기
                    if (giftScore[i] > giftScore[j]) {
                        nextMonth[i]++;
                    } else if (giftScore[i] < giftScore[j]) {
                        nextMonth[j]++;
                    }
                } else { // j가 더 많이 줬으면, j가 받기
                    nextMonth[j]++;
                }
            }
        }

        // 결과 계산
        int answer = 0;
        for (int num : nextMonth) {
            answer = Math.max(answer, num);
        }
        return answer;
    }
}

