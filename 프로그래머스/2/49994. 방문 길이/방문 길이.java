import java.util.*;

class Solution {
    public int solution(String dirs) {
        
        boolean[][][] visited = new boolean[11][11][4];
        
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        
        int idx = 0;
        int y = 5;
        int x = 5;
        int answer = 0;
        
        for (int i = 0; i < dirs.length(); i++) {
            char dir = dirs.charAt(i);
            if (dir == 'U') idx = 0;
            if (dir == 'R') idx = 1;
            if (dir == 'D') idx = 2;
            if (dir == 'L') idx = 3;
            
            int ny = y + dy[idx];
            int nx = x + dx[idx];
            
            if (ny < 0 || ny >= 11 || nx < 0 || nx >= 11) continue;
            if (!visited[ny][nx][idx]) {
                visited[ny][nx][idx] = true;
                visited[y][x][(idx + 2) % 4] = true;
                answer++;
            }
            y = ny;
            x = nx;
        }
        return answer;
    }
}