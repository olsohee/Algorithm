import java.util.*;

class Solution {
    
    int[][] users;
    int[] emoticons;
    int n, m;
    List<Result> answerList = new ArrayList<>();
    
    public int[] solution(int[][] users, int[] emoticons) {
        
        this.users = users;
        this.emoticons = emoticons;
        this.n = users.length;
        this.m = emoticons.length;
        
        // dfs로 각 이모티콘의 할인율 조합 구하기
        dfs(0, new int[m]);
        
        Collections.sort(answerList);
        
        return new int[]{answerList.get(0).joinCnt, answerList.get(0).salesAmount};
    }
    
    private void dfs(int idx, int[] discoutPercent) {
        if (idx == m) {
            getResult(discoutPercent);
            return;
        }
        
        for (int i = 1; i <= 4; i++) {
            discoutPercent[idx] = i * 10;
            dfs(idx + 1, discoutPercent);
        }
    }
    
    private void getResult(int[] discoutPercent) {
        int joinCnt = 0;
        int salesAmount = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int percent = users[i][0];
            int joinAmount = users[i][1];
            for (int j = 0; j < m; j++) {
                if (discoutPercent[j] >= percent) { // 구매
                    sum += emoticons[j] * (100 - discoutPercent[j]) / 100;
                } 
            }
            if (sum >= joinAmount) {
                joinCnt++;
            } else {
                salesAmount += sum;
            }
        }
        
        answerList.add(new Result(joinCnt, salesAmount));
    }
    
    class Result implements Comparable<Result> {
        int joinCnt;
        int salesAmount;
        
        public Result (int joinCnt, int salesAmount) {
            this.joinCnt = joinCnt;
            this.salesAmount = salesAmount;
        }
        
        @Override
        public int compareTo(Result o) {
            if (o.joinCnt == this.joinCnt) {
                return o.salesAmount - this.salesAmount;
            }
            return o.joinCnt - this.joinCnt;
        }
    }
}