import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int[] time = new int[360000];
        
        for (String log : logs) {
            int start = convertToTime(log.split("-")[0]);
            int end = convertToTime(log.split("-")[1]);
            time[start]++;
            time[end]--;
        }
        
        // 누적합을 통해 각 시간에 보는 시청자 수 구하기
        for (int i = 1; i < convertToTime(play_time); i++) {
            time[i] += time[i - 1];
        }
        
        // 슬라이딩 윈도우로 time 중 어느 범위가 최대 인원이 되는지 확인
        long sum = 0;
        int start = 0;
        int end = convertToTime(adv_time);
        for (int i = start; i < end; i++) {
            sum += time[i];
        }
        long max = sum;
        int answer = start; // 시작 시간
        
        while (end <= convertToTime(play_time)) {
            if (max < sum) {
                max = sum;
                answer = start;
            }
            sum -= time[start++];
            sum += time[end++];
        }
        
        return convertToString(answer);
    }
    
    private int convertToTime(String str) {
        String[] arr = str.split(":");
        return Integer.parseInt(arr[0]) * 60 * 60
            + Integer.parseInt(arr[1]) * 60 
            + Integer.parseInt(arr[2]);
    }
    
    private String convertToString(int time) {
        System.out.println("시간 = " +time);
        int hour = time / 3600;
        int minute = (time % 3600) / 60;
        int sec = (time % 3600) % 60;
        String str = "";
        
        if (hour < 10) {
            str += "0";
        }
        str += hour + ":";
        
        if (minute < 10) {
            str += "0";
        }
        str += minute + ":";
        
        if (sec < 10) {
            str += "0";
        }
        str += sec;
        return str;
    }
}