import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        int endTime = toInt(video_len);
        int now = toInt(pos);
        int openingStartTime = toInt(op_start);
        int openingEndTime = toInt(op_end);
        
        if (now >= openingStartTime && now <= openingEndTime) {
            now = openingEndTime;
        }
        
        for (String command : commands) {
            if (command.equals("next")) {
                now += 10;
                if (now > endTime) {
                    now = endTime;
                }
            }
            if (command.equals("prev")) {
                now -= 10;
                if (now < 0) {
                    now = 0;
                }
            }
            
            if (now >= openingStartTime && now <= openingEndTime) {
                now = openingEndTime;
            }

        }
        
        String minute = String.valueOf(now / 60);
        String sec = String.valueOf(now % 60);
        
        if (now / 60 < 10) {
            minute = "0" + minute;
        }
        if (now % 60 < 10) {
            sec = "0" + sec;
        }
        return minute + ":" + sec;
    }
    
    private int toInt(String time) {
        return Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]); 
    }
}