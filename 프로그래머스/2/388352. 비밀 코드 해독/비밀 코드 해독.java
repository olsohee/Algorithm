import java.util.*;

class Solution {
    
    int n;
    int[][] q;
    int[] ans;
    int answer;
    
    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        
        // 1~n까지의 수 중 5개를 고르는 조합 생성
        combination(new int[5], 0, 1);
         
        return answer; // 가능한 정수 조합 반환
    }
    
    private void combination(int[] arr, int idx, int start) {
        if (idx == 5) {
            if (canAnswer(arr)) {
                answer++;
            }
            return;
        }
        
        for (int i = start; i <= n; i++) {
            arr[idx] = i;
            combination(arr, idx + 1, i + 1);
        }
    }
    
    private boolean canAnswer(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        
        int correct = 0;
        
        for (int i = 0; i < q.length; i++) {
            int containCnt = 0;
            for (int j = 0; j < 5; j++) {
                if (set.contains(q[i][j])) {
                    containCnt++;
                }
            }
            if (containCnt == ans[i]) correct++;
        }
        
        if (correct == q.length) return true;
        return false;
    }
}