import java.util.*;

class Solution {
    
    int answer = 0; // 미로 탈출 최소 시간 (탈출 불가시 -1)
    
    public int solution(String[] maps) {
        
        int y = maps.length;
        int x = maps[0].length();
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        char[][] map = new char[y][x];
        int[][] visited = new int[y][x];
        int startX = 0; int startY = 0;
        int leverX = 0; int leverY = 0;
        
        // map 채우기

        for (int i = 0; i < y; i++) {
            char[] arr = maps[i].toCharArray();
            for (int j = 0; j < arr.length; j++) {
                map[i][j] = arr[j];
                if (arr[j] == 'S') {
                    startY = i;
                    startX = j;
                }
            }
        }
        
        // 1. 시작점(S) -> 레버(L)
        Queue<Point> que = new LinkedList<>();
        visited[startY][startX] = 1;
        que.add(new Point(startY, startX));
        
        boolean canGo = false;
        while (!que.isEmpty()) {
            Point now = que.poll();
            
            // 레버에 도착한 경우
            if (map[now.y][now.x] == 'L') {
                leverY = now.y;
                leverX = now.x;
                answer += visited[now.y][now.x] - 1;
                canGo = true;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;
                
                // 그래프 밖이면 패스
                if (nx < 0 || nx >= x || ny < 0 || ny >= y) {
                    continue;
                }
                // 벽이거나 이미 방문했으면 패스
                if (map[ny][nx] == 'X' || visited[ny][nx] != 0) continue;
                
                visited[ny][nx] = visited[now.y][now.x] + 1;
                que.add(new Point(ny, nx));
            }
        }
        if (!canGo) return -1;
        canGo = false;
        
        // 2. 레버(L) -> 출구(E)
        // visited 초기화 (1번에서 지난 길을 또 지날 수 있어야 하므로)
        visited = new int[y][x];
        que.clear();
        visited[leverY][leverX] = 1;
        que.add(new Point(leverY, leverX));
        
        while (!que.isEmpty()) {
            Point now = que.poll();
            
            // 출구 도착한 경우
            if (map[now.y][now.x] == 'E') {
                answer += visited[now.y][now.x] - 1;
                canGo = true;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;
                
                // 그래프 밖이면 패스
                if (nx < 0 || nx >= x || ny < 0 || ny >= y) {
                    continue;
                }
                // 벽이거나 이미 방문했으면 패스
                if (map[ny][nx] == 'X' || visited[ny][nx] != 0) continue;
                
                visited[ny][nx] = visited[now.y][now.x] + 1;
                que.add(new Point(ny, nx));
            }
        }
        
        if (!canGo) return -1;

        return answer;
    }
    
    class Point {
        int y, x;
        public Point (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}