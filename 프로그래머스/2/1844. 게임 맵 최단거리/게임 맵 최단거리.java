import java.util.*;

class Solution {
    
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    
    public int solution(int[][] maps) {
        
        int n = maps.length; // 세로
        int m = maps[0].length; // 가로
        
        int[][] visited = new int[n][m];
        
        // (0, 0)에서 bfs 시작
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0, 0));
        visited[0][0] = 1;
        
        while (!que.isEmpty()) {
            Node node = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + node.x;
                int ny = dy[i] + node.y;
                
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                if (visited[ny][nx] != 0 || maps[ny][nx] == 0) {
                    continue;
                } 
                
                que.add(new Node(ny, nx));
                visited[ny][nx] = visited[node.y][node.x] + 1;
            }
        }
        
        // 지나는 칸의 최솟값 (불가능하면 -1)
        if (visited[n - 1][m - 1] == 0) {
            return -1;
        } 
        return visited[n - 1][m - 1];
    }
    
    public class Node {
        int y, x;
        public Node (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}