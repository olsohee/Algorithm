import java.util.*;

class Solution {
    
    int[][] map = new int[101][101];
    boolean[][] visited = new boolean[101][101];
    int[][] cnt = new int[101][101];
    int answer = 0;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        for (int[] r : rectangle) {
            fillMap(r[0] * 2, r[1] * 2, r[2] * 2, r[3] * 2);
        }
        
        bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
        
        return answer / 2;
    }
    
    public void fillMap(int x1, int y1, int x2, int y2) {
        for (int i = y1; i <= y2; i++){
            for (int j = x1; j <= x2; j++) {
                // 이미 1이면 계속 1이어야 함
                if (map[i][j] == 1) {
                    continue;
                }
                
                if (i == y1 || i == y2 || j == x1 || j == x2) {
                    map[i][j] = 2;
                } else {
                    // 이미 2(테두리)여도 덮여서 1이 될 수 있음
                    map[i][j] = 1;
                }
            }
        }
    }
    
    public void bfs(int startX, int startY, int endX, int endY) {
        
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(startY, startX));
        visited[startY][startX] = true;
        
        while (!que.isEmpty()) {
            if (cnt[endY][endX] != 0) {
                answer = cnt[endY][endX];
                break;
            }
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;
                
                if (nx < 0 || nx > 100 || ny < 0 || ny > 100) continue;
                if (visited[ny][nx] || map[ny][nx] != 2) continue;
                
                que.add(new Node(ny, nx));
                visited[ny][nx] = true;
                cnt[ny][nx] = cnt[now.y][now.x] + 1;
            }
        }
    }
    
    public class Node {
        
        int y, x;
        
        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}