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
                if (!visited[i]) {
                    dfs(i);
                    answer++;
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