import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        if (k >= enemy.length) return enemy.length;
        
        // 가능한 라운드의 범위: 0 ~ enemy.length
        int start = 0;
        int end = enemy.length;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            long cnt = getEnemyCnt(k, enemy, mid); // 대적하는 적의 수 (k개의 적은 제외하고)
            if (cnt <= n) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }        

        if (end == 1) {
            if (enemy[0] <= n) return end;
            else return 0;
        }
        return end;
    }
    
    private long getEnemyCnt(int k, int[] enemy, int round) { 
        // 1~round까지 간다고 가정
        if (k >= round) {
            return 0; 
        }
        
        int[] enemyInRound = new int[round];
        for(int i = 0; i < round; i++) {
            enemyInRound[i] = enemy[i];
        }
        Arrays.sort(enemyInRound);
        
        // 적이 많은 k개 제외하고 대적
        long enemyCnt = 0;
        for (int i = 0; i < round - k; i++) {
            enemyCnt += enemyInRound[i];
            // System.out.println("더함 : " + enemyInRound[i]);
        }
        return enemyCnt;
    }
}