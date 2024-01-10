// class Solution {
//     static int n;
//     static int answer;
//     static int[][] graph;
//     static boolean[] visited;

//     public int solution(int n, int[][] computers) {
//         answer = 0;
//         this.n = n;
//         graph = new int[n][n];
//         visited = new boolean[n];
        
//         for(int i = 0; i < computers.length; i++) {
//             graph[i] = computers[i];
//         }
        
//         for(int i = 0; i < n; i++) {
//             for(int j = 0; j < n; j++) {
//                 if(!visited[i] && graph[i][j] == 1) {
//                     answer++;
//                     dfs(i);
//                 }
//             }
//         }
        
//         return answer;
//     }
    
//     public void dfs(int idx) {
        
//         visited[idx] = true;
        
//         for(int i = 0; i < n; i++) {
//             if(!visited[i] && graph[idx][i] == 1) {
//                 dfs(i);
//             }
//         }
//     }
// }

// 시간 복잡도: O(V+E) = 200 * 200
class Solution {

    int n;
    int[][] computers;
    boolean[] visited;

    public int solution(int n, int[][] computers) {

        this.n = n;
        this.computers = computers;
        visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1 && !visited[j]) {
                    answer++;
                    dfs(j);
                }
            }
        }
        return answer;
    }

    private void dfs(int j) {
        visited[j] = true;
        for (int i = 0; i < n; i++) {
            if (computers[j][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }
}
