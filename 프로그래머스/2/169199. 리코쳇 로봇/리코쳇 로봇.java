import java.util.*;

class Solution {
    
    int y, x;
    char[][] map;
    
    public int solution(String[] board) {
        y = board.length;
        x = board[0].length();
        map = new char[y][x];
        for (int i = 0; i < y; i++) {
            map[i] = board[i].toCharArray();
        }
        
        int startY = 0;
        int startX = 0;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (map[i][j] == 'R') {
                    startY = i;
                    startX = j;
                } 
            }
        }
        
        // 시작점에서 bfs 시작
        return bfs(startY, startX);
    }
    
    private int bfs(int startY, int startX) {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(startY, startX, 0));
        
        boolean[][] visited = new boolean[y][x];
        visited[startY][startX] = true;
        
        int answer = -1;
        
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};
        
        while (!que.isEmpty()) {
            Node now = que.poll();
            if (map[now.y][now.x] == 'G') {
                answer = now.cnt;
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                /* now.dir이
                0이면, 1로 이동 X
                1이면, 0으로 이동 X
                2이면, 3으로 이동 X
                3이면, 2로 이동 X
                */
                // if (lastDir == 0 && i == 1) continue;
                // if (lastDir == 1 && i == 0) continue;
                // if (lastDir == 2 && i == 3) continue;
                // if (lastDir == 3 && i == 2) continue;
                
                // i 방향으로 이동
                int ny = now.y;
                int nx = now.x;
                
                while (true) {
                    ny += dy[i];
                    nx += dx[i];
                    // 테두리이면 끝내기
                    if (ny < 0 || ny == y || nx < 0 || nx == x) {
                        break;
                    }
                    // 장애물 부딪히면 끝내기
                    if (map[ny][nx] == 'D') {
                        break;
                    }
                }
                
                
                if (i == 0) {
                    ny--;
                }
                if (i == 1) {
                    ny++;
                }
                if (i == 2) {
                    nx--;
                }
                if (i == 3) {
                    nx++;
                }
                
                if (!visited[ny][nx]) {
                    que.add(new Node(ny, nx, now.cnt + 1));
                    visited[ny][nx] = true;
                }
            }
        }
        
        return answer;
    }
    
    static class Node {
        int y, x;
        // int dir; // 해당 위치로 올 때 어떤 방향으로 왔는지 (0, 1, 2, 3)
        int cnt; // 움직인 횟수
        
        public Node(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}