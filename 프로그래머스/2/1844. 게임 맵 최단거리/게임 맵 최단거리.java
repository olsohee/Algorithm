import java.util.*;

class Solution {
    
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
        
    public int solution(int[][] maps) {
        
        // bfs로 이동 (0, 0에서 -> n, m으로)
        int n = maps.length;
        int m = maps[0].length;
        int[][] visited = new int[n][m];
        
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0, 0));
        visited[0][0] = 1;
        
        while (!que.isEmpty()) {
            Node node = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                if (visited[ny][nx] != 0 || maps[ny][nx] == 0) {
                    continue;
                }
                
                visited[ny][nx] = visited[node.y][node.x] + 1;
                que.add(new Node(ny, nx));
            }
        }
        
        // 지나가는 최소 칸수 (도착 불가하면 -1)
        if (visited[n - 1][m - 1] == 0) {
            return -1;
        }
        return visited[n - 1][m - 1];
    }
    
    public class Node {
        int y, x;
        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}