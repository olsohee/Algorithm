import java.util.*;

class Solution {
            int answer;
        int n;
        int[][] computers;
        boolean[] visited;

        public int solution(int n, int[][] computers) {
            this.n = n;
            this.computers = computers;
            visited = new boolean[n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (computers[i][j] == 1 && !visited[j]) {
                        dfs(j);
                        answer++;
                    }
                }
            }
            
            return answer;
        }

        private void dfs(int start) {
            visited[start] = true;

            for (int i = 0; i < n; i++) {
                if (computers[start][i] == 1 && !visited[i]) {
                    dfs(i);
                }
            }
        }
}