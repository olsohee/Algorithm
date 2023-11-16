class Solution {
    static int n;
    static int answer;
    static int[][] graph;
    static boolean[] visited;

    public int solution(int n, int[][] computers) {
        answer = 0;
        this.n = n;
        graph = new int[n][n];
        visited = new boolean[n];
        
        for(int i = 0; i < computers.length; i++) {
            graph[i] = computers[i];
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i] && graph[i][j] == 1) {
                    answer++;
                    dfs(i);
                }
            }
        }
        
        return answer;
    }
    
    public void dfs(int idx) {
        
        visited[idx] = true;
        
        for(int i = 0; i < n; i++) {
            if(!visited[i] && graph[idx][i] == 1) {
                dfs(i);
            }
        }
    }
}
