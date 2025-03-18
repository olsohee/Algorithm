import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        
        Arrays.sort(rocks);
        int start = 1;
        int end = distance;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            int cnt = 0; // 각 거리가 mid 이상이 되도록 바위를 제거한 개수
            int last = 0;
            for (int rock : rocks) {
                if (rock - last < mid) {
                    cnt++;
                } else {
                    last = rock;
                }
            } 
            // 마지막 바위와 도착 지점 간 거리가 mid보다 작으면 마지막 바위도 제거
            if (distance - last < mid) {
                cnt++; 
            }
            
            System.out.println("mid = " + mid + ", 제거개수 = " + cnt); 
            
            if (cnt > n) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(end);
        System.out.println(start);
        return end;
    }
}