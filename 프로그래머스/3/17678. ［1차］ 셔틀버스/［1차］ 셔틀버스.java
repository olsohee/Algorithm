import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        
        Queue<Integer> que = new PriorityQueue<>();
        for (String time : timetable) {
            que.add(convertToInt(time));
        }
        
        int cnt = 0;
        int now = convertToInt("09:00");
        int max = 0;
        int answer = 0;
        
        while (cnt <= n) {
            cnt++; // cnt번째 진행
            boolean isFull = false;
            int last = 0;
            for (int i = 0; i < m; i++) {
                if (que.isEmpty()) {
                    break;
                }
                if (que.peek() > now) {
                    break;
                }
                last = que.poll();
                if (i == m - 1) {
                    isFull = true;
                }
            }
            
            if (cnt == n) {
                if (isFull) { 
                    answer = last - 1;
                } else {
                    answer = now;
                }
            }
            
            now += t; // t시간 지남
        }
        
        return convertToString(answer);
    }
    
    private int convertToInt(String str) {
      return Integer.parseInt(str.split(":")[0]) * 60 + Integer.parseInt(str.split(":")[1]);
    }
    
    private String convertToString(int time) {
        int hour = time / 60;
        int min = time % 60;
        String str = "";
        if (hour < 10) {
            str += "0" + String.valueOf(hour);
        } else {
            str += String.valueOf(hour);
        }
        
        str += ":";
        
        if (min < 10) {
            str += "0" + String.valueOf(min);
        } else {
            str += String.valueOf(min);
        }
        return str;
    }
}