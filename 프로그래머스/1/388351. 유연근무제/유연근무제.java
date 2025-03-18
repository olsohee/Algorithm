import java.util.*;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int n = schedules.length;
        
        Set<Integer> weekend = new HashSet<>();
    
        if (startday == 7) {
            weekend.add(0);
            weekend.add(6);
        } else {
            weekend.add(6 - startday);
            weekend.add(6 - startday + 1);
        }
        
        for (int i = 0; i < n; i++) {
            boolean isSuccess = true;
            for (int j = 0; j < 7; j++) {
                if (weekend.contains(j)) continue; // 주말은 패스
                int time = timelogs[i][j]; // i번 직원이 j날에 출근한 시간
                if (convertToTime(schedules[i] + 10) < time) { // 지각한 경우
                    isSuccess = false; 
                    break;
                }
            }
            
            if (isSuccess) {
                answer++;
            }
        }
        
        return answer; // 상품 받는 지원수
    }
    
    private int convertToTime(int num) {
        int minute = num % 100;
        int hour = num / 100;
        if (minute >= 60) {
            hour++;
            minute -= 60;
            return hour * 100 + minute;
        } else {
            return num;
        }
    }
}