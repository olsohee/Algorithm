import java.util.*;

class Solution {
    
    int[] cards;
    int n;
    
    public int solution(int[] cards) {
        this.cards = cards;
        this.n = cards.length + 1; 
        this.cards = new int[n];
        for (int i = 0; i < cards.length; i++) {
            this.cards[i + 1] = cards[i];
        }
        
        // 1 ~ n-1 중 하나를 시작으로 열기
        int answer = 0;
        
        for (int i = 1; i < n; i++) {
            boolean[] open = new boolean[n];
            open[i] = true;
            int cnt1 = dfs(i, 1, open);
            
            if (cnt1 == n - 1) return 0;
            
            for (int j = 1; j < n; j++) {
                if (!open[j]) {
                    boolean[] copyOpen = new boolean[n];
                    for (int k = 0; k < n; k++) {
                        copyOpen[k] = open[k];
                    }
                    // boolean[] copyOpen = Arrays.clone(open);
                    copyOpen[j] = true;
                    int cnt2 = dfs(j, 1, copyOpen);
                    answer = Math.max(answer, cnt1 * cnt2);
                }
            }
        }
        
        return answer; // 1번 상자 그룹의 상자 수 * 2번 상자 그룹의 상자 수 (최고 점수)
    }
    
    private int dfs(int idx, int cnt, boolean[] open) {
        int next = cards[idx]; 
        if (open[next]) {
            return cnt;
        } else {
            open[next] = true;
            return dfs(next, cnt + 1, open);
        }
    }
}