import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        // 배열에 저장 (도난 당한 사람 -1, 여벌 있는 사람 1)
        int[] arr = new int[n + 1];
        for (int num : lost) {
            arr[num] = -1;
        }
        for (int num : reserve) {
            arr[num]++;
        }
        
        // 1번부터 N번까지 돌면서 체육복 빌리기
        for (int i = 1; i <= n; i++) {
            if (arr[i] == -1) {
                // 앞 학생에게 빌리기
                if (i - 1 >= 1 && arr[i - 1] == 1) {
                    arr[i] = 0; 
                    arr[i - 1]--;
                    continue;
                }
                // 뒤 학생에게 빌리기
                if (i + 1 <= n && arr[i + 1] == 1) {
                    arr[i] = 0;
                    arr[i + 1]--;
                }
            }
        }
        
        // 체육수업을 들을 수 있는 학생 수 구하기
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (arr[i] >= 0) answer++;
        }
        return answer;
    }
}