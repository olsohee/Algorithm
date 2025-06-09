import java.util.*;

class Solution {
    
    int h, w;
    int y, x;
    int[] dy = {-1, 0, 1, 0};
    int[] dx = {0, 1, 0, -1};
    char[][] map;
    
    public int[] solution(String[] park, String[] routes) {
        h = park.length;
        w = park[0].length();
        map = new char[park.length][park[0].length()];
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[0].length(); j++) {
                map[i][j] = park[i].charAt(j);
                if (map[i][j] == 'S') {
                    y = i;
                    x = j;
                }
            }
        }
        
        for (String route : routes) {
            String dir = route.split(" ")[0];
            int cnt = Integer.parseInt(route.split(" ")[1]);
            if (dir.equals("N")) {
                move(0, cnt);
            }
            if (dir.equals("E")) {
                move(1, cnt);
            }
            if (dir.equals("S")) {
                move(2, cnt);
            }
            if (dir.equals("W")) {
                move(3, cnt);
            }
        }
        return new int[]{y, x};
    }
    
    private void move(int dir, int cnt) {
        int moveY = y;
        int moveX = x;
        
        while (cnt > 0) {
            cnt--;
            moveY += dy[dir];
            moveX += dx[dir];
            if (moveY < 0 || moveY >= h || moveX < 0 || moveX >= w) return;
            if (map[moveY][moveX] == 'X') return;
        }
        
        y = moveY;
        x = moveX;
    }
}