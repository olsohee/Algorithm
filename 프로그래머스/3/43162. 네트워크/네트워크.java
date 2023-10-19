class Solution {

    int n;
    int[][] computers;
    boolean[] visited;
    int answer;

    public int solution(int n, int[][] computers) {

        this.n = n;
        this.computers = computers;
        visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(computers[i][j] == 1 && !visited[j]) {
                    answer++;
                    dfs(j);
                }
            }
        }
        return answer;
    }

    public void dfs(int index) {

        visited[index] = true;

        for(int i = 0; i < n; i++) {
            if(computers[index][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }
}

