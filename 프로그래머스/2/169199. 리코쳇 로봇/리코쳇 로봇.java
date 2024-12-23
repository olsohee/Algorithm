import java.util.*;

class Solution {
    
    char[][] map;
    boolean[][] visited;
    int r;
    int c;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};
    int answer = -1;
    
    public int solution(String[] board) {
        
        r = board.length;
        c = board[0].length();
        map = new char[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            char[] arr = board[i].toCharArray();
            for (int j = 0; j < c; j++) {
                map[i][j] = arr[j];
            }
        }
        
        // 시작점에서 bfs 시작
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'R') {
                    bfs(i, j);
                }
            }
        }
        
        return answer;
    }
    
    private void bfs(int y, int x) {
        
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y, x, 0));
        visited[y][x] = true;
        
        while (!que.isEmpty()) {
            Node now = que.poll();
            if (map[now.y][now.x] == 'G') {
                answer = now.cnt;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int ny = now.y;
                int nx = now.x;
                while (true) {
                    ny += dy[i];
                    nx += dx[i];
                    if (ny < 0 || ny >= r || nx < 0 || nx >= c) {
                        ny -= dy[i];
                        nx -= dx[i];
                        break;
                    }
                    if (map[ny][nx] == 'D') {
                        ny -= dy[i];
                        nx -= dx[i];
                        break;
                    }
                }
                
                if (visited[ny][nx]) {
                    continue;
                }
                
                que.add(new Node(ny, nx, now.cnt + 1));
                visited[ny][nx] = true;
            }
        }
        
        
    }
    
    private static class Node {
        
        int y, x;
        int cnt;
        
        public Node(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}