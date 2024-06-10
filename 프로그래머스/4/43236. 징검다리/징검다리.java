import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        
        Arrays.sort(rocks);
        int answer = 0;
        int left = 0;
        int right = distance;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            int cnt = 0; // 제거한 바위 개수
            int prev = 0; // 바위 간 거리 파악을 위한 시작 점
            for (int i = 0; i < rocks.length; i++) {
                // mid보다 바위 간 거리가 작으면 바위 제거
                if (rocks[i] - prev < mid) {
                    cnt++;
                    if (cnt > n) {
                        break;
                    }
                } else {
                    prev = rocks[i];
                }
            }
            if (distance - prev < mid) {
                cnt++;
            }
            
            // 현재 mid가 답이 될 수 없는 경우(-> mid 줄이기)
            if (cnt > n) {
                right = mid - 1;
            } 
            // 현재 mid가 답이 될 수 있는 경우(-> 최댓값을 구해야 하므로 mid 늘리기)
            else {
                answer = mid;
                left = mid + 1;
            }
        }
        
        return answer;
    }
}