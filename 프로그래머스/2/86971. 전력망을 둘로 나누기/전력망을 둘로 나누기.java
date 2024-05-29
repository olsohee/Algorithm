class Solution {
    
    int n;
    int[][] wires;
    int answer = Integer.MAX_VALUE;
    boolean[] visited;
    boolean[][] map;
    
    public int solution(int n, int[][] wires) {
        
        this.n = n;
        this.wires = wires;
        
        for (int i = 0; i < n - 1; i++) {
            visited = new boolean[n + 1];
            map = new boolean[n + 1][n + 1];
            
            for (int j = 0; j < n - 1; j++) {
                if (i == j) continue;
                map[wires[j][0]][wires[j][1]] = true;
                map[wires[j][1]][wires[j][0]] = true;
            }
            
            int result1 = dfs(wires[i][0]);
            int result2 = n - result1;
            answer = Math.min(answer, Math.abs(result1 - result2));
        }
        return answer;
    }
    
    public int dfs(int start) {
        visited[start] = true;
        int sum = 1;
        
        for (int i = 1; i <= n; i++) {
            if (map[start][i] && !visited[i]) {
                sum += dfs(i);
            }
        }
        
        return sum;
    }
}